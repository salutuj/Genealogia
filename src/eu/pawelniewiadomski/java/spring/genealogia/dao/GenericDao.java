package eu.pawelniewiadomski.java.spring.genealogia.dao;


import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

import java.util.List;
import java.util.Map;


/**
 * Generic DAO interface.
 */
public interface GenericDao<M extends AbstractModel>
{

	/**
	 * Searches for all instances of model given as a generic type.
	 * 
	 * @return List of all instances of model given as a generic type.
	 */
	List<M> find();

	/**
	 * Searches for all instances of model given as a generic type matching given parameters.
	 * 
	 * @param params
	 *           parameters to add to search query as {@link Map} with parameter name as a key and parameter value as a
	 *           value.
	 */
	List<M> find(final Map<String, ? extends Object> params);


}
