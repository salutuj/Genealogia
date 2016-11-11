package eu.pawelniewiadomski.java.spring.genealogia.services.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.gedcom4j.parser.GedcomParser;

import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;

public class DefaultGedcomService implements GedcomService {

  protected static final Log LOG = LogFactory.getLog(DefaultGedcomService.class);
  private static final long UTF8_BYTE_ORDER_MARKER = 0xEFBBBFL;

  private GedcomParser gedcomParser;

  public void setGedcomParser(GedcomParser gedcomParser) {
    this.gedcomParser = gedcomParser;
  }

  public GedcomParser getGedcomParser() {
    return gedcomParser;
  }

  @Override
  public boolean parseGedcomAsString(String gedcom) {
    if (gedcom == null || gedcom.isEmpty()) throw new NullPointerException("gedcom string is null or empty");
    try {
      return parseGedcomAsByteArray( convertGedcomNonBOMUtf8ToByteArray(gedcom));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean parseGedcomAsByteArray(final byte [] gedcomAsBytes) {
    if (gedcomAsBytes == null || gedcomAsBytes.length > 0) throw new NullPointerException("gedcomAsBytes is null or empty");        
    return parseGedcomAsInputStream(new BufferedInputStream(new ByteArrayInputStream(gedcomAsBytes)));
  }

  @Override
  public boolean parseGedcomAsInputStream(final BufferedInputStream gedcomAsInputStream) {
    if (gedcomAsInputStream == null) throw new NullPointerException("gedcomAsInputStream is null or empty");
    try {
      getGedcomParser().load(gedcomAsInputStream);
      return true;
    } catch (GedcomParserException e) {
      LOG.error("Incorrect syntax in gedcom string", e);
      return false;
    } catch (IOException e) {
      LOG.error("IOException occured when parsing gedcom data", e);
      return false;
    }
  }

  @Override
  public Individual getIndividualById(String id) {
    Map<String, Individual> individuals = getGedcomParser().getGedcom().getIndividuals();
    if (individuals == null || individuals.isEmpty()) {
      LOG.info("No individuals found in gedcom parsed data cache. At least one is expected");
      return null;
    }
    return individuals.get(GedcomService.id2Xref(id));
  }

  @Override
  public Family getFamilyById(String id) {
    Map<String, Family> families = getGedcomParser().getGedcom().getFamilies();
    if (families == null || families.isEmpty()) {
      LOG.info("No families found in gedcom parsed data cache. At least one  is expected");
      return null;
    }
    return families.get(GedcomService.id2Xref(id));
  }

  static byte[] convertGedcomNonBOMUtf8ToByteArray(final String gedcom) throws UnsupportedEncodingException {
    byte[] processedGedcom = gedcom.replaceAll("\\\\n", "\n").getBytes("UTF-8");
    byte[] bomGedcom = new byte[3 + processedGedcom.length];
    bomGedcom[0] = (byte) ((UTF8_BYTE_ORDER_MARKER & 0xFF0000) >> 16);
    bomGedcom[1] = (byte) ((UTF8_BYTE_ORDER_MARKER & 0xFF00) >> 8);
    bomGedcom[2] = (byte) (UTF8_BYTE_ORDER_MARKER & 0xFF);
    System.arraycopy(processedGedcom, 0, bomGedcom, 3, processedGedcom.length);
    return bomGedcom;
  }
}
