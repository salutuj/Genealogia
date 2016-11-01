package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;

public class JsonPersonConverter extends JsonConverter<PersonModel>{

  @Autowired
  AbstractConverter<PersonEventModel, JsonObjectOrArray> personEventConverter;
  
  public void setPersonEventConverter(AbstractConverter<PersonEventModel, JsonObjectOrArray> personEventConverter) {
    this.personEventConverter = personEventConverter;
  }
  
  @Override
  public JsonObjectOrArray convert(PersonModel source) {
    Map<String, Object> target = new HashMap<String,Object>();
    target.put("personId", source.getId());
    target.put("firstName", source.getFirstName());
    target.put("lastName", source.getLastName());
    target.put("age", source.getAge());
    PersonEventModel birth = source.getBirth();    
    target.put("birth", birth != null  ? personEventConverter.convert(birth) : null);
    PersonEventModel death = source.getDeath();    
    target.put("death", death != null ? personEventConverter.convert(death) : null);    
    target.put("relationToParent", source.getRelationToParent());
    target.put("familiesAsChild", source.getFamiliesAsChild());
    target.put("familiesAsSpouse", source.getFamiliesAsSpouse());
    target.put("father", source.getFather());
    target.put("mother", source.getMother());
    return new JsonObjectOrArray(convertObjectToJson(target));
  }

}
