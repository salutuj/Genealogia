package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEvent;
import org.gedcom4j.model.IndividualEventType;
import org.gedcom4j.model.PersonalName;
import org.gedcom4j.model.StringWithCustomTags;
import org.gedcom4j.parser.GedcomParser;

import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomUtils;


public class DefaultPersonService implements PersonService{

  protected static final Log LOG = LogFactory.getLog(DefaultPersonService.class);
  
  protected IndividualDao individualDao;  
  
  
	@Override
	public PersonModel findPersonById(String individualId) {
	  if ( individualId == null){
      LOG.error("Individual id is null");
      return null;
    }    
    GedcomIndividualModel gedcomIndividual = getIndividualDao().findIndividualById(individualId);
    return convertGedcomIndividualToPerson(gedcomIndividual);
	}

	@Override
	public Collection<PersonModel> findAllPersonById(String personId) {
		// TODO Auto-generated method stub
		return new ArrayList<PersonModel>();
	}

	@Override
  public PersonModel convertGedcomIndividualToPerson(GedcomIndividualModel gedcomIndividual) {
	  if ( gedcomIndividual == null){
	    LOG.error("gedcomIndividual is null");
	    return null;
	  } 	    
    PersonModel personModel = new PersonModel();
    personModel.setId(gedcomIndividual.getId());
    GedcomParser parser = new GedcomParser();
    try {
      byte [] gedcomAsBytes = GedcomUtils.convertGedcomNonBOMUtf8ToByteArray(gedcomIndividual.getGedcom());
      parser.load(new BufferedInputStream(new ByteArrayInputStream(gedcomAsBytes)));
    } catch ( GedcomParserException e) {
      LOG.error("Incorrect syntax in gedcom string", e);
      return null;
    } catch (IOException e) {      
      LOG.error("IOException occured when parsing gedcom data", e);
      return null;
    }
    Map<String, Individual> gedcomIndividuals = parser.getGedcom().getIndividuals();
    if ( gedcomIndividuals == null || gedcomIndividuals.isEmpty() ){
      LOG.error("No indivduals were provided in gedcom string. At least one is expected");
      return null;
    }      
    Individual individual = gedcomIndividuals.get(GedcomUtils.id2Xref(gedcomIndividual.getId()));
    List<PersonalName> names = individual.getNames();
    if ( names != null && names.size() > 0){
      StringWithCustomTags name = names.get(0).getGivenName();
      if (name != null){
        final String fullName[] = name.getValue().split(" ");
        personModel.setFirstName(fullName[0]);
        personModel.setLastName(fullName[1]);
      }
    }    
    personModel.setAge(0);
    final List<IndividualEvent> birthEventsList = individual.getEventsOfType(IndividualEventType.BIRTH);
    if (birthEventsList != null && birthEventsList.size() > 0 ){ 
      final IndividualEvent birthEvent = birthEventsList.get(0);
      personModel.setDateOfBirth(GedcomUtils.convertGedcomDate(birthEvent.getDate().getValue()));
      personModel.setPlaceOfBirth(birthEvent.getPlace().getPlaceName());
    }
    final List<IndividualEvent> deathEventsList = individual.getEventsOfType(IndividualEventType.DEATH);
    if (deathEventsList != null && deathEventsList.size() > 0 ){
      final IndividualEvent deathEvent = deathEventsList.get(0);
      personModel.setDateOfBirth(GedcomUtils.convertGedcomDate(deathEvent.getDate().getValue()));
      personModel.setPlaceOfDeath(deathEvent.getPlace().getPlaceName());
    }
    personModel.setRelationToParent(individual.getSex().getValue().equals("M") ? "Son" : "Daughter");
    return personModel;
  }
	
	
  public void setIndividualDao(IndividualDao individualDao) {
    this.individualDao = individualDao;
  }
  
  public IndividualDao getIndividualDao() {
    return individualDao;
  }
}


