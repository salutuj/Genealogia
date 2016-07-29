package eu.pawelniewiadomski.java.spring.genealogia.converters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

public abstract class JsonConverter<M extends AbstractModel> implements AbstractConverter<M, String> {

  @Override
  public abstract String convert(M source);

  public static String convertToJsonObject(Map<String, Object> params) {
    StringBuffer json = new StringBuffer(256);
    json.append("{");
    if (params.size() > 0) {
      for (Entry<String, Object> param : params.entrySet()) {

        json.append('"').append(param.getKey()).append("\": ");
        Object val = param.getValue();
        if (val instanceof String)
          json.append('"').append(val).append('"');
        else json.append(val.toString());
        json.append(',');
      }
      json.deleteCharAt(json.length() - 1);
    }
    json.append("}");
    return json.toString();
  }

  @SuppressWarnings("unchecked")
  public static String convertToJsonArray(List<Object> array) {
    StringBuffer json = new StringBuffer(256);
    json.append("[");
    for (Object item : array) {
      if (item instanceof String)
        json.append('"').append(item).append('"');
      else if (item instanceof HashMap)
        json.append(convertToJsonObject((Map<String, Object>) item));
      else if (item instanceof List)
        json.append(convertToJsonArray((List<Object>) item));
      else json.append(item.toString());
    }
    json.append("]");
    return json.toString();
  }
}
