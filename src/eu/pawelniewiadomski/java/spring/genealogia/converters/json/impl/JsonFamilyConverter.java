package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;

public class JsonFamilyConverter extends JsonConverter<FamilyModel>{

  @Autowired
  AbstractConverter<PersonModel, JsonObjectOrArray> personConverter;
  
  public void setPersonConverter(AbstractConverter<PersonModel, JsonObjectOrArray> personConverter) {
    this.personConverter = personConverter;
  }
  
  @Override
  public JsonObjectOrArray convert(FamilyModel source) {
    Map<String, Object> target = new HashMap<String,Object>();    
    target.put("id", source.getId());
    target.put("name", source.getFamilyName());
    PersonModel father = source.getFather();
    target.put("father", father != null ? personConverter.convert(father) : null);
    PersonModel mother = source.getMother();
    target.put("mother", mother != null ? personConverter.convert(mother) : null);
    Collection<JsonObjectOrArray> children = new ArrayList<JsonObjectOrArray>(); 
    for ( PersonModel child : source.getChildren() )
      children.add(personConverter.convert(child));
    target.put("children", children);    
    return new JsonObjectOrArray(convertObjectToJson(target));
  }
  
  

}
