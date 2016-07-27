package eu.pawelniewiadomski.java.spring.genealogia.converters.impl;

import java.util.HashMap;
import java.util.Map;

import eu.pawelniewiadomski.java.spring.genealogia.converters.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;

public class FamilyConverter extends JsonConverter<FamilyModel>{

  @Override
  public String convert(FamilyModel source) {
    Map<String, Object> target = new HashMap<String,Object>();
    target.put("personId", source.getId());
    target.put("father", source.getFather());
    target.put("mother", source.getMother());    
    return convertToJsonObject(target);
  }

}
