package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomUtils;

public class DefaultFamilyService implements FamilyService {

  protected static final Log LOG = LogFactory.getLog(DefaultFamilyService.class);

  protected FamilyDao familyDao;
  protected PersonService personService;

  @Override
  public FamilyModel findFamilyById(String familyId) {
    if ( familyId == null){
      LOG.error("Family id is null");
      return null;
    }    
    GedcomFamilyModel gedcomFamily= getFamilyDao().findFamilyById(familyId);
    return convertGedcomToFamily(gedcomFamily);
  }

  @Override
  public Collection<FamilyModel> findAllFamilies() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public FamilyModel getDefaultFamily() {
    GedcomFamilyModel defaultGedcomFamily = getFamilyDao().findFamilyById("F1");
    if (defaultGedcomFamily == null) {
      LOG.error("defaultGedcomFamily is null");
      return null;
    }
    return convertGedcomToFamily(defaultGedcomFamily);
  }

  public FamilyModel convertGedcomToFamily(GedcomFamilyModel gedcomFamily) {
    FamilyModel familyModel = new FamilyModel();
    familyModel.setId(gedcomFamily.getId());
    GedcomParser parser = new GedcomParser();
    try {
      byte [] gedcomAsBytes = GedcomUtils.convertGedcomNonBOMUtf8ToByteArray(gedcomFamily.getGedcom());
      parser.load(new BufferedInputStream(new ByteArrayInputStream(gedcomAsBytes)));
    } catch (GedcomParserException e) {
      LOG.error("Incorrect syntax in gedcom string", e);
      return null;
    } catch (IOException e) {
      LOG.error("IOException occured when parsing gedcom data", e);
      return null;
    }
    Map<String, Family> gedcomFamilies = parser.getGedcom().getFamilies();
    if (gedcomFamilies == null || gedcomFamilies.isEmpty()) {
      LOG.error("No families found in gedcom data. At least one must is expected");
      return null;
    }
    Family family = gedcomFamilies.get(GedcomUtils.id2Xref(gedcomFamily.getId()));
    // TODO familyModel.setFamilyName(family.toString());
    familyModel.setFather(getPersonService().findPersonById(gedcomFamily.getHusbandId()));
    familyModel.setMother(getPersonService().findPersonById(gedcomFamily.getWifeId()));
    List<Individual> children = family.getChildren();
    if (children == null || children.isEmpty())
      familyModel.setChildren(Collections.emptyList());
    else {
      Collection<PersonModel> childrenModels = new ArrayList<PersonModel>();
      for (Individual child : children)
        childrenModels.add(getPersonService().findPersonById(GedcomUtils.xref2Id(child.getXref())));
      familyModel.setChildren(childrenModels);
    }
    return familyModel;
  }



  public void setPersonService(PersonService personService) {
    this.personService = personService;
  }

  public PersonService getPersonService() {
    return personService;
  }

  public void setFamilyDao(FamilyDao familyDao) {
    this.familyDao = familyDao;
  }

  public FamilyDao getFamilyDao() {
    return familyDao;
  }

}
