package eu.pawelniewiadomski.java.spring.genealogia.utils;

import java.util.Date;

public class GedcomDateConverter {

  private static final String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

  public static Date convertGedcomDate(String gedcomDate){    
    int day=1, month=1, year=2015-1900, i = 0;
    String [] dateParts = gedcomDate.split(" ");
    day = Integer.valueOf(dateParts[0]);
    year = Integer.valueOf(dateParts[2]) - 1900;    
    for (String monthAbbr : months){
      if (dateParts[1].equals(monthAbbr)){
         month = i;
         break;
      }
      else
        i++;
    }        
    return new Date(day, month, year);
  }
}
