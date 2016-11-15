package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.model.FamilyChild;
import org.gedcom4j.model.FamilySpouse;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEvent;
import org.gedcom4j.model.IndividualEventType;
import org.gedcom4j.model.PersonalName;
import org.gedcom4j.model.StringWithCustomTags;

import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel.EventType;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PlaceModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;

public class DefaultPersonService implements PersonService {

  protected static final Log LOG = LogFactory.getLog(DefaultPersonService.class);

  protected IndividualDao individualDao;

  protected GedcomService gedcomService;

  protected FamilyService familyService;

  @Override
  public PersonModel findPersonById(String individualId) {
    if (individualId == null) {
      LOG.error("Individual id is null");
      return null;
    }
    Individual individual = gedcomService.getIndividualById(individualId);
    if (individual == null || individual.getEvents() == null) {
      GedcomIndividualModel gedcomIndividual = getIndividualDao().findIndividualById(individualId);
      if (gedcomIndividual != null) {
        gedcomService.parseGedcomAsString(gedcomIndividual.getGedcom());
      } else LOG.warn("Individual " + individualId + " not found!");
      individual = gedcomService.getIndividualById(individualId);
    }
    return individual != null ? convertIndividualToPersonModel(individualId, individual) : null;
  }

  @Override
  public Collection<PersonModel> findAllPersonById(String personId) {
    // TODO Auto-generated method stub
    return new ArrayList<PersonModel>();
  }

  public PersonModel convertIndividualToPersonModel(final String personId, final Individual individual) {
    PersonModel personModel = new PersonModel();
    personModel.setId(personId);
    List<PersonalName> names = individual.getNames();
    if (names != null && names.size() > 0) {
      StringWithCustomTags name = names.get(0).getGivenName();
      if (name != null && name.getValue() != null) {
        final String firstNames[] = name.getValue().split(" ");
        personModel.setFirstName(firstNames[0]);
        if (firstNames.length > 1) personModel.setMiddleName(firstNames[1]);
      }
      StringWithCustomTags surname = names.get(0).getSurname();
      if (surname != null && surname.getValue() != null) personModel.setLastName(surname.getValue().split(" ")[0]);
    }
    personModel.setAge(0);
    final List<IndividualEvent> birthEventsList = individual.getEventsOfType(IndividualEventType.BIRTH);
    if (birthEventsList != null && birthEventsList.size() > 0) {
      final IndividualEvent birthEvent = birthEventsList.get(0);
      PersonEventModel birthModel = createPersonEvent(EventType.BIRTH, birthEvent.getDate().getValue(), birthEvent.getPlace().getPlaceName(),
          birthEvent.getPlace().getLatitude().getValue(), birthEvent.getPlace().getLongitude().getValue());
      personModel.setBirth(birthModel);
    }
    final List<IndividualEvent> deathEventsList = individual.getEventsOfType(IndividualEventType.DEATH);
    if (deathEventsList != null && deathEventsList.size() > 0) {
      final IndividualEvent deathEvent = deathEventsList.get(0);
      PersonEventModel deathModel = createPersonEvent(EventType.DEATH, deathEvent.getDate().getValue(), deathEvent.getPlace().getPlaceName(),
          deathEvent.getPlace().getLatitude().getValue(), deathEvent.getPlace().getLongitude().getValue());
      personModel.setDeath(deathModel);
    }
    StringWithCustomTags sex = individual.getSex();
    if (sex != null && sex.getValue() != null) personModel.setRelationToParent(sex.getValue().equals("M") ? "Son" : "Daughter");

    if (individual.getFamiliesWhereChild() != null) {
      Collection<String> familiesWhereChild = new ArrayList<String>();
      for (FamilyChild familyWhereChild : individual.getFamiliesWhereChild())
        familiesWhereChild.add(GedcomService.xref2Id(familyWhereChild.getFamily().getXref()));
      personModel.setFamiliesAsChild(familiesWhereChild);
    }
    if (individual.getFamiliesWhereSpouse() != null) {
      Collection<String> familiesWhereSpouse = new ArrayList<String>();
      for (FamilySpouse familyWhereSpouse : individual.getFamiliesWhereSpouse())
        familiesWhereSpouse.add(GedcomService.xref2Id(familyWhereSpouse.getFamily().getXref()));
      personModel.setFamiliesAsSpouse(familiesWhereSpouse);
    }
    return personModel;
  }

  private PersonEventModel createPersonEvent(EventType type, String date, String place, String latitude, String longitude) {
    PersonEventModel eventModel = new PersonEventModel();
    eventModel.setType(type);
    eventModel.setEventStartDate(GedcomService.convertGedcomDate(date));
    PlaceModel eventPlace = new PlaceModel();
    eventPlace.setName(place);
    eventPlace.setGpsLat(GedcomService.positionValue2Double(latitude));
    eventPlace.setGpsLong(GedcomService.positionValue2Double(longitude));
    eventModel.setPlace(eventPlace);
    return eventModel;
  }

  @Override
  public PersonModel findPersonWithAncestors(final String personId, final int maxLevel) {
    if (personId == null) {
      LOG.error("Person id is null");
      return null;
    }
    PersonModel person = findPersonById(personId);
    addAncestors(person, maxLevel);
    return person;
  }

  //TODO: check for better algorithm
  private void addAncestors(final PersonModel person, int currentLevel){
    if ( currentLevel-- == 0 || person.getFamiliesAsChild() == null )
       return;
    String familyId = person.getFamiliesAsChild().iterator().next();
    FamilyModel family = familyService.findFamilyById(familyId);
    if ( family != null){
      PersonModel father = family.getFather();
      if (hasData(father)) {
        addAncestors(father, currentLevel);    
        person.setFather(father);
      }
      PersonModel mother = family.getMother();
      if ( hasData(mother)){        
        addAncestors(mother, currentLevel);
        person.setMother(mother);
      }     
    }
  }

  private boolean hasData(final PersonModel person) {
    return person != null && (person.getEvents() != null || person.getFirstName() != null 
      || person.getLastName() != null || person.getBirth()!= null || person.getDeath() != null
      );
  }

  public void setIndividualDao(IndividualDao individualDao) {
    this.individualDao = individualDao;
  }

  public void setGedcomService(GedcomService gedcomService) {
    this.gedcomService = gedcomService;
  }

  public void setFamilyService(FamilyService familyService) {
    this.familyService = familyService;
  }

  public IndividualDao getIndividualDao() {
    return individualDao;
  }

  public GedcomService getGedcomService() {
    return gedcomService;
  }

  public FamilyService getFamilyService() {
    return familyService;
  }

}
