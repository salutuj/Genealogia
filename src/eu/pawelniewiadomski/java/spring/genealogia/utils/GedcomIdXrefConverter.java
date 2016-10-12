package eu.pawelniewiadomski.java.spring.genealogia.utils;

public class GedcomIdXrefConverter {

  public static String id2Xref(String id) {
    return "@" + id + "@";
  }

  public static  String xref2Id(String xref) {
    return xref.split("@")[1];
  }
}
