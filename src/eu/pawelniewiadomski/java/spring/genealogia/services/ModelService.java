package eu.pawelniewiadomski.java.spring.genealogia.services;

import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

public class ModelService {

  AbstractModel create(Class model){
    
    return null;//(AbstractModel)(model.getClass().getConstructor().newInstance());
    
  }
}
