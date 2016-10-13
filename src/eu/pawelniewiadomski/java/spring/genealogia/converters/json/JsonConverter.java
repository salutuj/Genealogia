package eu.pawelniewiadomski.java.spring.genealogia.converters.json;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

public abstract class JsonConverter<M extends AbstractModel> implements AbstractConverter<M, JsonObjectOrArray> {

  @Override
  public abstract JsonObjectOrArray convert(M source);

 
  public static String convertObjectToJson(Map<String, Object> params) {
    StringBuffer json = new StringBuffer(2048);
    json.append("{");
    if (params.size() > 0) {
      for (Entry<String, Object> param : params.entrySet()) {
        json.append('"').append(param.getKey()).append("\": ");
        json.append(convertValueToJson(param.getValue()));
        json.append(',');
      }
      json.setCharAt(json.length() - 1, '}');
    } else json.append("}");
    return json.toString();
  }

  public static String convertArrayToJson(Collection<Object> array) {
    StringBuffer json = new StringBuffer(1024);
    json.append("[");
    if (array.size() > 0) {
      for (Object item : array) {    
        json.append(convertValueToJson(item));
        json.append(',');
      }
      json.setCharAt(json.length() - 1, ']');
    } else json.append("]");
    return json.toString();
  }
  
  @SuppressWarnings("unchecked")
  private static String convertValueToJson(final Object value){
    StringBuffer json = new StringBuffer(256);
    if (value instanceof Collection) 
      json.append(convertArrayToJson((Collection<Object>) value));
    else if (value instanceof Map)
      json.append(convertObjectToJson((Map<String, Object>) value));
    else if (value instanceof String || value instanceof Date) 
      json.append('"').append(value).append('"');
    else if (value instanceof Number || value instanceof Boolean || value instanceof JsonObjectOrArray)
      json.append(value.toString());
    else if (value != null)
      json.append('"').append(value.toString()).append('"');
    else 
      json.append("null");
    return json.toString();
  }
}
