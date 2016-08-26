package eu.pawelniewiadomski.java.spring.genealogia.dao;

import java.util.Collection;

import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;

public interface InividualEventDao extends GenericDao<PersonEventModel>{

  /**
   * Finds all {@link PersonEventModel} for specific gedcom individual.
   * 
   * @return an empty collection if no event was found.
   */
  
  Collection<PersonEventModel> findEventsOfIndividual(String individualId);
  
  /**
   * Finds the event by the given id.
   * 
   * @return <code>null</code> if no {@link PersonEventModel} by the given <code>id</code> was found.
   * @param id
   *           the id of the event
   * 
   * 
   */
  PersonEventModel findEventById(String id);
  
  /**
   * save event
   *  
   * @param event 
   *           event to be saved
   * 
   * 
   */
  void saveEvent(PersonEventModel event);
  
}
