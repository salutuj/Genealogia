package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.parser.GedcomParser;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;

public abstract class BaseTest extends AbstractTestNGSpringContextTests {

  protected GedcomParser gedcomParser;
  
  public abstract void initMocks();
  
  public void setupClass( String sampleFile) throws IOException, GedcomParserException{
    gedcomParser = new GedcomParser();
    InputStream is = getClass().getResourceAsStream(sampleFile);
    Assert.notNull(is, "sampleFile is not valid");
    gedcomParser.load(new BufferedInputStream(is));
  }
}
