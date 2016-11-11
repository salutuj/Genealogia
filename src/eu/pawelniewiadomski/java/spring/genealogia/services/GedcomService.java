package eu.pawelniewiadomski.java.spring.genealogia.services;

import java.io.BufferedInputStream;
import java.util.Date;

import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;

public interface GedcomService {
  boolean parseGedcomAsString(final String gedcom);
  boolean parseGedcomAsByteArray(final byte [] gedcom);
  boolean parseGedcomAsInputStream(final BufferedInputStream gedcom);

  Individual getIndividualById(final String id);

  Family getFamilyById(final String id);

  static final String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

  static Date convertGedcomDate(String gedcomDate) {
    int day = 1, month = 1, year = 2015 - 1900, i = 0;
    String[] dateParts = gedcomDate.split(" ");
    day = Integer.valueOf(dateParts[0]);
    year = Integer.valueOf(dateParts[2]) - 1900;
    for (String monthAbbr : months) {
      if (dateParts[1].equals(monthAbbr)) {
        month = i;
        break;
      } else i++;
    }
    return new Date(day, month, year);
  }

  static String id2Xref(String id) {
    return "@" + id + "@";
  }

  static String xref2Id(String xref) {
    return xref.split("@")[1];
  }

  static double positionValue2Double(String positionValue) {
    if (positionValue.startsWith("N") || positionValue.startsWith("E"))
      return Double.parseDouble(positionValue.substring(1));
    else return -Double.parseDouble(positionValue.substring(1));
  }

  static String double2PositionValue(Double position, boolean isLatitude) {
    if (position < 0)
      return isLatitude ? "S" + position : "W" + position;
    else return isLatitude ? "N" + position : "E" + position;
  }

  

}
