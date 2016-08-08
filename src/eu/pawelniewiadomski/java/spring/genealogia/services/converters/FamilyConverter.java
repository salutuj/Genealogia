package eu.pawelniewiadomski.java.spring.genealogia.services.converters;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;

public class FamilyConverter {

  public FamilyModel convert(GedcomFamilyModel gedcomFamily){
    FamilyModel family = new FamilyModel();
    family.setId(gedcomFamily.getId());
    return family;
  }
  
  protected void convertGedcom( FamilyModel family, GedcomFamilyModel gedcomFamily){
    //family.
  }
}
