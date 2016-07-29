package eu.pawelniewiadomski.java.spring.genealogia.controllers.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;

/**
 * 
 * @author pawel.niewiadomski
 * 
 *
 */
@Controller(value = "jspGenealogiaController")
public class JspGenealogiaController {

//  protected static final Log LOG = LogFactory.getLog(JspGenealogiaController.class);

//  @Autowired
//  PersonService personService;
//
//  @Autowired
//  FamilyService familyService;

//  @RequestMapping(value = { "/" }, method = RequestMethod.GET)
//  public String getDefaultFamily() {
//    return "genealogia";
//  }

  // @RequestMapping(value = { "/" }, method = RequestMethod.GET)
  // public ModelAndView getDefaultFamily() {
  // Map<String, Object> models = new HashMap<String, Object>();
  // FamilyModel defaultFamily = familyService.getDefaultFamily();
  // models.put("defaultFamily", defaultFamily);
  // return new ModelAndView("genealogia", models);
  // }
  //
  // @RequestMapping(value = { "/family-{familyId}" }, method =
  // RequestMethod.GET)
  // public ModelAndView getFamily( @PathVariable String familyId, ModelMap
  // model) {
  // Map<String, Object> models = new HashMap<String, Object>();
  // FamilyModel family = familyService.findFamilyById(familyId);
  // models.put("family", family);
  // return new ModelAndView("familyView", models);
  // }
  //
  // @RequestMapping(value = { "/person-{personId}" }, method =
  // RequestMethod.GET)
  // public String getPerson(@PathVariable String personId, ModelMap model) {
  // PersonModel person = personService.findPersonById(personId);
  // model.addAttribute("person", person);
  // return "person";
  // }

}
