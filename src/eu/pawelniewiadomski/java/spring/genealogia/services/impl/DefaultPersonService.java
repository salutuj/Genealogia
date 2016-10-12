package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomDateConverter;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomIdXrefConverter;

public class DefaultPersonService implements PersonService{

  protected static final Log LOG = LogFactory.getLog(DefaultPersonService.class);
  
  protected IndividualDao individualDao;  
  
  
	@Override
	public PersonModel findPersonById(String individualId) {
	  if ( individualId == null){
      LOG.error("Individual id is null");
      return null;
    }    
    GedcomIndividualModel gedcomIndividual = getIndividualDao().findIndividualById(individualId);
    return convertGedcomIndividualToPerson(gedcomIndividual);
	}

	@Override
	public Collection<PersonModel> findAllPersonById(String personId) {
		// TODO Auto-generated method stub
		return new ArrayList<PersonModel>();
	}

	@Override
  public PersonModel convertGedcomIndividualToPerson(GedcomIndividualModel gedcomIndividual) {
	  if ( gedcomIndividual == null){
	    LOG.error("gedcomIndividual is null");
	    return null;
	  } 	    
    PersonModel personModel = new PersonModel();
    personModel.setId(gedcomIndividual.getId());
    GedcomParser parser = new GedcomParser();
    try {
      byte [] gedcomAsBytes = gedcomIndividual.getGedcom().replaceAll("\\\\n", "\n").getBytes("UTF-8");
      parser.load(new BufferedInputStream(new ByteArrayInputStream(gedcomAsBytes)));
    } catch ( GedcomParserException e) {
      LOG.error("Incorrect syntax in gedcom string", e);
      return null;
    } catch (IOException e) {      
      LOG.error("IOException occured when parsing gedcom data", e);
      return null;
    }
    Map<String, Individual> gedcomIndividuals = parser.getGedcom().getIndividuals();
    if ( gedcomIndividuals == null || gedcomIndividuals.isEmpty() ){
      LOG.error("No indivduals were provided in gedcom string. At least one is expected");
      return null;
    }      
    Individual individual = gedcomIndividuals.get(GedcomIdXrefConverter.id2Xref(gedcomIndividual.getId()));
    personModel.setFirstName(individual.getNames().get(0).getGivenName().getValue().split(" ")[0]);
    personModel.setLastName(individual.getNames().get(0).getGivenName().getValue().split(" ")[1]);
    personModel.setAge(0);
    personModel.setDateOfBirth(GedcomDateConverter.convertGedcomDate(individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate()
        .getValue()));
    personModel.setPlaceOfBirth(individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getPlace().getPlaceName());
    return personModel;
  }
	
	
  public void setIndividualDao(IndividualDao individualDao) {
    this.individualDao = individualDao;
  }
  
  public IndividualDao getIndividualDao() {
    return individualDao;
  }
}


