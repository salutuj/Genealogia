package eu.pawelniewiadomski.java.spring.genealogia.dao;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;


/**
 * Dao for {@link GedcomIndividualModel} access.
 * 
 * @spring.bean individualDao
 * 
 */

public interface IndividualDao extends GenericDao<GedcomIndividualModel>{

	/**
	 * Finds all {@link GedcomIndividualModel} in the system.
	 * 
	 * @return an empty collection if no individual was found.
	 */
	
	Collection<GedcomIndividualModel> findIndividuals();
	
	/**
	 * Finds the individual by the given id.
	 * 
	 * @return <code>null</code> if no {@link GedcomIndividualModel} by the given <code>id</code> was found.
	 * @param id
	 *           the id of the individual
	 * 
	 * 
	 */
	GedcomIndividualModel findIndividualById(String id);
	
	/**
	 * Finds the individual by the given name.
	 * 
	 * @return <code>null</code> if no {@link GedcomIndividualModel} by the given <code>name</code> was found.
	 * @param name
	 *           the name of the individual
	 * 
	 * 
	 */
	GedcomIndividualModel findIndividualByName(String name);
	
	/**
	 * 
	 * @param individual
	 */
	void saveIndividual(GedcomIndividualModel individual);
	
}
