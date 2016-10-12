package eu.pawelniewiadomski.java.spring.genealogia.utils;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class GedcomUtils {

  private static final String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
  private static final long UTF8_BYTE_ORDER_MARKER = 0xEFBBBFL;
      
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
  
  public static String id2Xref(String id) {
    return "@" + id + "@";
  }

  public static  String xref2Id(String xref) {
    return xref.split("@")[1];
  }
  
  public static byte[] convertGedcomNonBOMUtf8ToByteArray(final String gedcom) throws UnsupportedEncodingException{
    byte [] processedGedcom = gedcom.replaceAll("\\\\n", "\n").getBytes("UTF-8");    
    byte [] bomGedcom = new byte[3+processedGedcom.length];
    bomGedcom[0] = (byte)((UTF8_BYTE_ORDER_MARKER & 0xFF0000) >> 16);
    bomGedcom[1] = (byte)((UTF8_BYTE_ORDER_MARKER & 0xFF00) >> 8);
    bomGedcom[2] = (byte)(UTF8_BYTE_ORDER_MARKER & 0xFF);
    System.arraycopy(processedGedcom, 0, bomGedcom, 3, processedGedcom.length);
    return bomGedcom;
  }
}
