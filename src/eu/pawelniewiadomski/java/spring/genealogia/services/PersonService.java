package eu.pawelniewiadomski.java.spring.genealogia.services;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;

public interface PersonService {
	PersonModel findPersonById(String personId);
	Collection<PersonModel> findAllPersonById(String personId);
  PersonModel findPersonWithAncestors(String individualId, int maxLevel);	
}
