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

public class DefaultFamilyService implements FamilyService{

  protected static final Log LOG = LogFactory.getLog(DefaultFamilyService.class);
  
  private FamilyDao familyDao;  
  private PersonService personService;
  
	@Override
	public FamilyModel findFamilyById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<FamilyModel> findAllFamilies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyModel getDefaultFamily() {	  	  
    GedcomFamilyModel defaultGedcomFamily = familyDao.findFamilyById("1");
    if ( defaultGedcomFamily == null)
      throw new NullPointerException("family is null");
    return convertGedcomToFamily( defaultGedcomFamily);
	}
	
  public FamilyModel convertGedcomToFamily(GedcomFamilyModel gedcomFamily){
    FamilyModel familyModel = new FamilyModel();
    familyModel.setId(gedcomFamily.getId());
    GedcomParser parser = new GedcomParser();
    try {
      parser.load(new BufferedInputStream(new ByteArrayInputStream(gedcomFamily.getGedcom().getBytes())));
    } catch (GedcomParserException e) {
      LOG.error("Incorrect syntax in gedcom string", e);
      return null;
    } catch (IOException e) {      
      LOG.error("IOException occured when parsing gedcom data", e);
      return null;
    }
    Map<String, Family> gedcomFamilies = parser.getGedcom().getFamilies();
    if ( gedcomFamilies == null || gedcomFamilies.isEmpty()){
      LOG.error("No families found in gedcom data. At least one must is expected");
    }
    Family family = gedcomFamilies.get(id2Xref(gedcomFamily.getId()));    
    //TODO familyModel.setFamilyName(family.toString());        
    familyModel.setFather(personService.findPersonById(gedcomFamily.getHusbandId()));            
    familyModel.setMother(personService.findPersonById(gedcomFamily.getWifeId()));
    List<Individual> children = family.getChildren();
    if ( children == null || children.isEmpty())
      familyModel.setChildren(Collections.emptyList());
    else{
      Collection<PersonModel> childrenModels = new ArrayList<PersonModel>();
      for (Individual child : children)
        childrenModels.add(personService.findPersonById(xref2Id(child.getXref())));
      familyModel.setChildren(childrenModels);
    }
    return familyModel;
  }

  private String id2Xref(String id){
    return "@" + id + "@";
  }
  
  private String xref2Id(String xref){
    return xref.split("@")[1];
  }
}
