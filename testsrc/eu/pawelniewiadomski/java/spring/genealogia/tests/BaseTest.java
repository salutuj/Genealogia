package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.parser.GedcomParser;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

public abstract class BaseTest extends AbstractTestNGSpringContextTests {

  protected GedcomParser gedcomParser;

  public abstract void initMocks();

  public boolean parseGedcomFile(final String filename) {
    Assert.assertNotNull(filename);
    gedcomParser = new GedcomParser();
    InputStream is = getClass().getResourceAsStream(filename);
    Assert.assertNotNull(is, "sampleFile is not valid");
    try {
      gedcomParser.load(new BufferedInputStream(is));
      return true;
    } catch (IOException | GedcomParserException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean parseGedcom(final String gedcom) {
    Assert.assertNotNull(gedcom);
    gedcomParser = new GedcomParser();
    BufferedInputStream bis;
    try {
      bis = new BufferedInputStream(new ByteArrayInputStream(gedcom.getBytes("UTF-8")));
      gedcomParser.load(bis);
      return true;
    } catch (IOException | GedcomParserException e) {
      e.printStackTrace();
    }
    return false;
  }

  public String inputStreamToString(final InputStream is) throws IOException {
    final StringBuilder out = new StringBuilder();
    final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    try {
      String line;
      while ((line = in.readLine()) != null)
        out.append(line);
    } finally {
      in.close();
    }
    return out.toString();
  }
}
