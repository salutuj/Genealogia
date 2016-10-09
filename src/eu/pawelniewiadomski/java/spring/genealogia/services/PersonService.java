package eu.pawelniewiadomski.java.spring.genealogia.services;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;

public interface PersonService {
	PersonModel findPersonById(String personId);
	Collection<PersonModel> findAllPersonById(String personId);
  PersonModel convertGedcomIndividualToPerson(GedcomIndividualModel gedcomIndividual);	
}
