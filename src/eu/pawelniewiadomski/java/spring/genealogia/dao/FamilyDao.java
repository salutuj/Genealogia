package eu.pawelniewiadomski.java.spring.genealogia.dao;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;

public interface FamilyDao extends GenericDao<FamilyModel>{

  public GedcomFamilyModel findFamilyById(final String id);
}
