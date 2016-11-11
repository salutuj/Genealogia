package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;

public class DefaultFamilyService implements FamilyService {

  protected static final Log LOG = LogFactory.getLog(DefaultFamilyService.class);

  protected FamilyDao familyDao;
  protected PersonService personService;
  protected GedcomService gedcomService;
  
  @Override
  public FamilyModel getDefaultFamily() {
    return findFamilyById("F1");
  }
  
  @Override
  public FamilyModel findFamilyById(String familyId) {
    if ( familyId == null){
      LOG.error("Family id is null");
      return null;
    }                
    // TODO: invalidate caching, move call to dao to gedcomService(?) 
    Family family = gedcomService.getFamilyById(familyId);
    if ( family == null || family.getEvents() == null){
      GedcomFamilyModel gedcomFamily= getFamilyDao().findFamilyById(familyId);
      gedcomService.parseGedcomAsString(gedcomFamily.getGedcom());
      family = gedcomService.getFamilyById(familyId);
    }
    //TODO: maybe more caching should be provided, also 
    return convertFamilyToFamilyModel(familyId, family);
  }

  @Override
  public Collection<FamilyModel> findAllFamilies() {
    // TODO Auto-generated method stub
    return null;
  }


  protected FamilyModel convertFamilyToFamilyModel(final String familyId, final Family family) {
    FamilyModel familyModel = new FamilyModel();
    familyModel.setId(familyId);        
    // TODO familyModel.setFamilyName(family.toString());
    final String fatherId = GedcomService.xref2Id(family.getHusband().getXref());
    familyModel.setFather(getPersonService().findPersonById(fatherId));
    final String motherId = GedcomService.xref2Id(family.getWife().getXref());
    familyModel.setMother(getPersonService().findPersonById(motherId));
    List<Individual> children = family.getChildren();
    if (children == null || children.isEmpty())
      familyModel.setChildren(Collections.emptyList());
    else {
      Collection<PersonModel> childrenModels = new ArrayList<PersonModel>();
      for (Individual child : children)
        childrenModels.add(getPersonService().findPersonById(GedcomService.xref2Id(child.getXref())));
      familyModel.setChildren(childrenModels);
    }
    return familyModel;
  }



  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  public void setFamilyDao(FamilyDao familyDao) {
    this.familyDao = familyDao;
  }
  
  public void setGedcomService(GedcomService gedcomService) {
    this.gedcomService = gedcomService;
  }
  
  public PersonService getPersonService() {
    return personService;
  }
  
  public FamilyDao getFamilyDao() {
    return familyDao;
  }
 
  public GedcomService getGedcomService() {
    return gedcomService;
  }
}
