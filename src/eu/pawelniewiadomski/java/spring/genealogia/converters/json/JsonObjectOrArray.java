package eu.pawelniewiadomski.java.spring.genealogia.converters.json;

public class JsonObjectOrArray {
  private final String flattened;
  
  public JsonObjectOrArray(final String flattened) {
    this.flattened = flattened;
  }
  
  @Override
  public String toString() {
   return flattened;
  }
}
