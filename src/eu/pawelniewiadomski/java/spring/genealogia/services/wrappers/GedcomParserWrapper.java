package eu.pawelniewiadomski.java.spring.genealogia.services.wrappers;

import org.gedcom4j.parser.GedcomParser;

public class GedcomParserWrapper {

  public GedcomParser parser;
  
  public GedcomParserWrapper(){
    parser = new GedcomParser();    
  }
  
  public void loadFromModel(){
    
  }
}
