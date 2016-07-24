package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;

public class DefaultPersonService implements PersonService{

	@Override
	public PersonModel findPersonById(String personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<PersonModel> findAllPersonById(String personId) {
		// TODO Auto-generated method stub
		return new ArrayList<PersonModel>();
	}

	
}
