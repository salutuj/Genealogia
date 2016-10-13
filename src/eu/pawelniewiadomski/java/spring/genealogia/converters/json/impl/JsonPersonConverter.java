package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.HashMap;
import java.util.Map;

import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;

public class JsonPersonConverter extends JsonConverter<PersonModel>{

  @Override
  public JsonObjectOrArray convert(PersonModel source) {
    Map<String, Object> target = new HashMap<String,Object>();
    target.put("personId", source.getId());
    target.put("firstName", source.getFirstName());
    target.put("lastName", source.getLastName());
    target.put("age", source.getAge());
    target.put("dateOfBirth", source.getDateOfBirth());
    target.put("placeOfBirth", source.getPlaceOfBirth());
    return new JsonObjectOrArray(convertObjectToJson(target));
  }

}
