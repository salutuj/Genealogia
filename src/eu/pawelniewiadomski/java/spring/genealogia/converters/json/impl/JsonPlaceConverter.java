package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.HashMap;
import java.util.Map;

import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.PlaceModel;

public class JsonPlaceConverter extends JsonConverter<PlaceModel> {

  @Override
  public JsonObjectOrArray convert(PlaceModel source) {
    Map<String, Object> target = new HashMap<String,Object>();
    target.put("name", source.getName());
    target.put("latitude", source.getGpsLat());
    target.put("longitude", source.getGpsLong());    
    return new JsonObjectOrArray(convertObjectToJson(target));
  }
}
