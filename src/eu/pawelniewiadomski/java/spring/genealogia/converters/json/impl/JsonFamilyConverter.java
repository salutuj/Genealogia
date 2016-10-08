package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObject;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;

public class JsonFamilyConverter extends JsonConverter<FamilyModel>{

  @Autowired
  AbstractConverter<PersonModel, String> personConverter;
  
  public void setPersonConverter(AbstractConverter<PersonModel, String> personConverter) {
    this.personConverter = personConverter;
  }
  
  @Override
  public String convert(FamilyModel source) {
    Map<String, Object> target = new HashMap<String,Object>();    
    target.put("id", source.getId());
    target.put("name", source.getFamilyName());
    target.put("father", new JsonObject(personConverter.convert(source.getFather())));
    target.put("mother", new JsonObject(personConverter.convert(source.getMother())));   
    
    return convertToJsonObject(target);
  }
  
  

}
