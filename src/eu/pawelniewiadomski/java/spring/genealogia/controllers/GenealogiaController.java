package eu.pawelniewiadomski.java.spring.genealogia.controllers;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;

/**
 * 
 * @author pawel.niewiadomski
 */

@Controller(value="genealogiaController")
@RequestMapping(produces = "application/json; charset=UTF-8")
public class GenealogiaController {

  protected static final Log LOG = LogFactory.getLog(GenealogiaController.class);

  @Autowired
  FamilyService familyService;

  @Autowired
  PersonService personService;

  @Autowired
  private AbstractConverter<FamilyModel, ? extends Object> familyConverter;

  @Autowired
  private AbstractConverter<PersonModel, ? extends Object> personConverter;

  @RequestMapping(value = { "/defaultFamily.json" }, method = RequestMethod.GET)
  public @ResponseBody String getDefaultFamily() {    
    FamilyModel defaultFamily = familyService.getDefaultFamily();
    return familyConverter.convert(defaultFamily).toString();
  }

  @RequestMapping(value = { "family-{familyId}.json" }, method = RequestMethod.GET)
  public @ResponseBody String getFamily(@PathVariable String familyId) {
    FamilyModel family = familyService.findFamilyById(familyId);
    return familyConverter.convert(family).toString();
  }

  @RequestMapping(value = { "person-{personId}.json" }, method = RequestMethod.GET)
  public @ResponseBody String getPerson(@PathVariable String personId) {
    PersonModel person = personService.findPersonById(personId);
    return personConverter.convert(person).toString();
  }

}
