package eu.pawelniewiadomski.java.spring.genealogia.services;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;

public interface FamilyService {
	FamilyModel findFamilyById(String id);
	Collection<FamilyModel> findAllFamilies();
	FamilyModel getDefaultFamily();
}
