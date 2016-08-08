package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.IOException;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.parser.GedcomParser;

public abstract class BaseTest {

  protected GedcomParser gedcomParser;
  
  public abstract void initMocks();
  
  public void setupClass(String sampleFile) throws IOException, GedcomParserException{
    gedcomParser = new GedcomParser();
    gedcomParser.load(new BufferedInputStream(getClass().getResourceAsStream(sampleFile)));
  }
}
