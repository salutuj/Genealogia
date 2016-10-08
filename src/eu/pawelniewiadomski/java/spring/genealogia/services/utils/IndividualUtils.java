package eu.pawelniewiadomski.java.spring.genealogia.services.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;
import org.hsqldb.lib.StringInputStream;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomDateConverter;

public class IndividualUtils {

  public static PersonModel convertFromGedcom(GedcomIndividualModel gedcomFather) {
    PersonModel personModel = new PersonModel();    
    personModel.setId(gedcomFather.getId());
    GedcomParser parser = new  GedcomParser();
    try {
      parser.load(new BufferedInputStream(new StringReader(gedcomFather.getGedcom())));
    } catch (IOException | GedcomParserException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Individual individual = parser.getGedcom().getIndividuals().get(gedcomFather.getId());    
    personModel.setFirstName(individual.getNames().get(0).getGivenName().getValue().split(" ")[0]);
    personModel.setLastName(individual.getNames().get(0).getGivenName().getValue().split(" ")[1]);
    personModel.setAge(0);
    personModel.setDateOfBirth(GedcomDateConverter.convertGedcomDate(individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().getValue()));
    personModel.setPlaceOfBirth(individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getPlace().getPlaceName());
    return personModel;
  }
  

}
