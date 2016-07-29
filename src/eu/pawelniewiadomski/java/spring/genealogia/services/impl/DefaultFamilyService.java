package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;

public class DefaultFamilyService implements FamilyService{

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
    FamilyModel defaultFamily = new FamilyModel();
    defaultFamily.setId("1");
    defaultFamily.setFamilyName("Niewiadomscy");
    PersonModel father = new PersonModel();
    father.setId("1");
    father.setFirstName("Paweł");
    father.setLastName("Niewiadomski");
    father.setAge(33);
    father.setDateOfBirth(new Date(83,9,7));
    father.setPlaceOfBirth("Katowice");
    defaultFamily.setFather(father);
    PersonModel mother = new PersonModel();
    mother.setId("2");
    mother.setFirstName("Magdalena");
    mother.setMaidenName("Niewiadomska");
    mother.setLastName("Niewiadomska");
    mother.setAge(31);
    mother.setDateOfBirth(new Date(85,7,6));
    mother.setPlaceOfBirth("Oświęcim");
    defaultFamily.setMother(mother);
    PersonModel child1 = new PersonModel();
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
    defaultFamily.setChildren(children);
		return defaultFamily;
	}

}
