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
 *         It’s a pretty straight-forward Spring based controller.
 * 
 * @Controller indicates that this class is a controller handling the requests
 *             with pattern mapped by @RequestMapping. Here with ‘/’, it is
 *             serving as default controller.
 * 
 *             Method listEmployees, annotated with @RequestMethod.GET, handling
 *             both the default URL ‘/’ as well as ‘/list’. It acts as handle
 *             for initial page of application, showing a list of existing
 *             employees.
 * 
 *             Method newEmployee is handling the GET request for the new
 *             employee registration page, showing page backed by a model
 *             Employee object.
 * 
 *             Method saveEmployee is annotated with @RequestMethod.POST, and
 *             will handle the form-submission POST requests for new employee
 *             registration (‘/new’). Notice the parameters and their orders in
 *             this method.
 * @Valid asks spring to validate the associated object(Employee). BindingResult
 *        contains the outcome of this validation and any error that might have
 *        occurred during this validation. Notice that BindingResult must come
 *        right after the validated object else spring won’t be able to validate
 *        and an exception been thrown. In case of validation failure, custom
 *        error messages(as we have configured in step 4) are shown.
 * 
 *        We have also included code to check for SSN uniqueness as it is
 *        declared to be unique in database. Before saving/updating an employee,
 *        we are checking if the SSN is unique. If not, we generate validation
 *        error and redirect to registration page. This piece of code
 *        demonstrate a way to fill it custom errors outside the validation
 *        framework as well while still using internationalized messages.
 * 
 *        Method editEmployee takes you to registration page with employee
 *        details filled in, while updateEmployee gets called when you click on
 *        update button after possible updation on gui.
 * 
 *        Method deleteEmployee is handling the deletion of an employee by it’s
 *        SSN number. Notice @PathVariable , which indicates that this parameter
 *        will be bound to variable in URI template (SSN in our case). As for as
 *        Annotation based configuration goes,this is all we need to do.
 * 
 *
 */
@Controller
@RequestMapping("/")
public class JspGenealogiaController {

	protected static final Log LOG = LogFactory.getLog(JspGenealogiaController.class);
	
	@Autowired
	PersonService personService;

	@Autowired
	FamilyService familyService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView getDefaultFamily() {
		Map<String, Object> models = new HashMap<String, Object>();
		FamilyModel defaultFamily = familyService.getDefaultFamily();
		models.put("defaultFamily", defaultFamily);
		return new ModelAndView("defaultFamilyView", models);
	}

	@RequestMapping(value = { "/family-{familyId}" }, method = RequestMethod.GET)
	public ModelAndView getFamily( @PathVariable String familyId, ModelMap model) {
		Map<String, Object> models = new HashMap<String, Object>();
		FamilyModel family = familyService.findFamilyById(familyId);
		models.put("family", family);
		return new ModelAndView("familyView", models);
	}
	
	@RequestMapping(value = { "/person-{personId}" }, method = RequestMethod.GET)
	public String getPerson(@PathVariable String personId, ModelMap model) {
		PersonModel person = personService.findPersonById(personId);
		model.addAttribute("person", person);
		return "person";
	}

}
