package eu.pawelniewiadomski.java.spring.genealogia.converters.json.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PlaceModel;

public class JsonPersonEventConverter extends JsonConverter<PersonEventModel> {

  @Autowired
  AbstractConverter<PlaceModel, JsonObjectOrArray> placeConverter;
  
  public void setPlaceConverter(AbstractConverter<PlaceModel, JsonObjectOrArray> placeConverter) {
    this.placeConverter = placeConverter;
  }
  
  @Override
  public JsonObjectOrArray convert(PersonEventModel source) {
    Map<String, Object> target = new HashMap<String,Object>();
    target.put("type", source.getType().toString());
    PlaceModel place = source.getPlace();
    target.put("place", place != null ? placeConverter.convert(place) : null);
    target.put("startDate", source.getEventStartDate());
    target.put("stopDate", source.getEventStopDate());    
    return new JsonObjectOrArray(convertObjectToJson(target));
  }

}
