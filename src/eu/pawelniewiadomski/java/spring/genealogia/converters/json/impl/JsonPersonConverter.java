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
    target.put("dateOfDeath", source.getDateOfDeath());
    target.put("placeOfBirth", source.getPlaceOfBirth());
    target.put("placeOfDeath", source.getPlaceOfDeath());
    target.put("relationToParent", source.getRelationToParent());
    target.put("familiesAsChild", source.getFamiliesAsChild());
    target.put("familiesAsSpouse", source.getFamiliesAsSpouse());
    target.put("father", source.getFather());
    target.put("mother", source.getMother());
    return new JsonObjectOrArray(convertObjectToJson(target));
  }

}
