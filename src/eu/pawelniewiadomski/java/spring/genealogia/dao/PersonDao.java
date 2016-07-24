package eu.pawelniewiadomski.java.spring.genealogia.dao;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;


/**
 * Dao for {@link PersonModel} access.
 * 
 * @spring.bean personDao
 * 
 */

public interface PersonDao extends GenericDao<PersonModel>{

	/**
	 * Finds all {@link PersonModel} in the system.
	 * 
	 * @return an empty collection if no person was found.
	 */
	
	Collection<PersonModel> findPersons();
	
	/**
	 * Finds the person by the given id.
	 * 
	 * @return <code>null</code> if no {@link PersonModel} by the given <code>id</code> was found.
	 * @param id
	 *           the id of the person
	 * 
	 * 
	 */
	PersonModel findPersonById(String id);
	
	/**
	 * Finds the person by the given name.
	 * 
	 * @return <code>null</code> if no {@link PersonModel} by the given <code>name</code> was found.
	 * @param name
	 *           the name of the person
	 * 
	 * 
	 */
	PersonModel findPersonByName(String name);
}
