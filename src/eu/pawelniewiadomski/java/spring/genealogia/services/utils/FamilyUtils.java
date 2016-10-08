package eu.pawelniewiadomski.java.spring.genealogia.services.utils;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.parser.GedcomParser;
import org.hsqldb.lib.StringInputStream;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;

public class FamilyUtils {

  public static FamilyModel convertFromGedcom(GedcomFamilyModel gedcomFamily){
    FamilyModel familyModel = new FamilyModel();
    familyModel.setId(gedcomFamily.getId());
    GedcomParser parser = new  GedcomParser();
    try {
      parser.load(new BufferedInputStream(new StringInputStream(gedcomFamily.getGedcom())));
    } catch (IOException | GedcomParserException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Family family = parser.getGedcom().getFamilies().get(gedcomFamily.getId());    
    familyModel.setFamilyName(family.toString());    
    return familyModel;
  }
  
}
