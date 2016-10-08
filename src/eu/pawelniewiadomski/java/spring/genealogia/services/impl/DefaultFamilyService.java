package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.gedcom4j.exception.GedcomParserException;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.utils.FamilyUtils;
import eu.pawelniewiadomski.java.spring.genealogia.services.utils.IndividualUtils;

public class DefaultFamilyService implements FamilyService{

  private FamilyDao familyDao;
  private IndividualDao individualDao;
  
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
    GedcomIndividualModel gedcomFather = individualDao.findIndividualById(defaultGedcomFamily.getHusbandId());
    if ( gedcomFather == null)
      throw new NullPointerException("father individual is null");
    GedcomIndividualModel gedcomMother = individualDao.findIndividualById(defaultGedcomFamily.getWifeId());
    if ( gedcomMother == null)
      throw new NullPointerException("mother individual is null");        
    FamilyModel defaultFamily = FamilyUtils.convertFromGedcom(defaultGedcomFamily);
    defaultFamily.setFamilyName("Niewiadomscy");    
    PersonModel father = IndividualUtils.convertFromGedcom(gedcomFather);    
    defaultFamily.setFather(father);    
    PersonModel mother = IndividualUtils.convertFromGedcom(gedcomFather);    
    defaultFamily.setFather(father);
    defaultFamily.setMother(mother);
    /*PersonModel child1 = new PersonModel();
    child1.setId("3");
    child1.setFirstName("Maria");
    child1.setMiddleName("Niewiadomska");
    child1.setMaidenName("Niewiadomska");
    child1.setLastName("Niewiadomska");
    child1.setAge(1);
    child1.setDateOfBirth(new Date(83,9,7));
    child1.setPlaceOfBirth("Katowice");
    Collection<PersonModel> children = new ArrayList<PersonModel>();
    children.add(child1);
    defaultFamily.setChildren(children);*/
		return defaultFamily;
	}

	
}
