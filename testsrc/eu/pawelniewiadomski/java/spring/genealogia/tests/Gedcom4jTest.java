package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEventType;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;
import org.springframework.context.annotation.DependsOn;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import sun.awt.image.ByteArrayImageSource;

public class Gedcom4jTest {

  private GedcomParser gedcomParser;
  private HashMap<String, String> memGedcomDb;
  private String familyDataF1;
  private String familyDataF3;
  private String personDataI2;
  private String personDataI6;
  private String personDataI181;

  @BeforeClass
  public void setupBeforeClass() {
    StringBuilder familyData = new StringBuilder(128);
    familyData.append("0 @F1@ FAM\n");
    familyData.append("1 CHIL @I2@\n");
    familyData.append("1 HUSB @I4@\n");
    familyData.append("1 MARR\n");
    familyData.append("2 TYPE Religious\n");
    familyData.append("2 DATE 11 JUL 1981\n");
    familyData.append("2 PLAC Katowice\n");
    familyData.append("1 WIFE @I9@\n");
    familyData.append("1 CHIL @I10@\n");
    familyData.append("1 CHAN\n");
    familyData.append("2 DATE 21 APR 2014\n");
    familyData.append("3 TIME 21:38:12\n");
    familyData.append("2 _WT_USER pawel\n");
    familyDataF1 = familyData.toString();

    familyData = new StringBuilder(128);
    familyData.append("0 @F3@ FAM\n");
    familyData.append("1 WIFE @I6@\n");
    familyData.append("1 HUSB @I2@\n");
    familyData.append("1 MARR\n");
    familyData.append("2 DATE 15 AUG 2012\n");
    familyData.append("2 PLAC Kęty\n");
    familyData.append("1 CHAN\n");
    familyData.append("2 DATE 25 JAN 2015\n");
    familyData.append("3 TIME 00:02:42\n");
    familyData.append("2 _WT_USER pawel\n");
    familyData.append("1 CHIL @I181@\n");
    familyDataF3 = familyData.toString();

    StringBuilder personData = new StringBuilder(128);
    personData.append("0 @I2@ INDI\n");
    personData.append("1 NAME Paweł Henryk Piotr /Niewiadomski/\n");
    personData.append("2 GIVN Paweł Henryk Piotr\n");
    personData.append("2 SURN Niewiadomski\n");
    personData.append("2 NICK salutuj\n");
    personData.append("2 _AKA /afro/\n");
    personData.append("1 SEX M\n");
    personData.append("1 BIRT\n");
    personData.append("2 DATE 07 OCT 1983\n");
    personData.append("2 PLAC Katowice\n");
    personData.append("1 FAMC @F1@\n");
    personData.append("1 FAMS @F3@\n");
    personData.append("1 CHAN\n");
    personData.append("2 DATE 03 MAR 2014\n");
    personData.append("3 TIME 20:08:07\n");
    personData.append("2 _WT_USER pawel\n");
    personData.append("1 OBJE @M1@");
    personDataI2 = personData.toString();

    personData = new StringBuilder(128);
    personData.append("0 @I6@ INDI\n");
    personData.append("1 NAME Magdalena Lidia /Zelewska/\n");
    personData.append("2 GIVN Magdalena Lidia\n");
    personData.append("2 SURN Zelewska\n");
    personData.append("2 _MARNM Magdalena Lidia /Niewiadomska/\n");
    personData.append("1 SEX F\n");
    personData.append("1 BIRT\n");
    personData.append("2 DATE 06 AUG 1985\n");
    personData.append("2 PLAC Oświęcim\n");
    personData.append("1 FAMS @F3@\n");
    personData.append("1 OBJE @M5@\n");
    personData.append("1 CHAN\n");
    personData.append("2 DATE 06 MAY 2014\n");
    personData.append("3 TIME 20:51:10\n");
    personData.append("2 _WT_USER pawel\n");
    personData.append("1 FAMC @F50@\n");
    personDataI6 = personData.toString();

    personData = new StringBuilder(128);
    personData.append("0 @I181@ INDI\n");
    personData.append("1 NAME Maria Magdalena /Niewiadomska/\n");
    personData.append("2 GIVN Maria Magdalena\n");
    personData.append("2 SURN Niewiadomska\n");
    personData.append("1 SEX F\n");
    personData.append("1 BIRT\n");
    personData.append("2 DATE 13 JAN 2015\n");
    personData.append("2 PLAC Bogucice (Katowice)\n");
    personData.append("1 FAMC @F3@\n");
    personData.append("2 PEDI birth\n");
    personData.append("1 BAPM\n");
    personData.append("2 DATE 07 MAR 2015\n");
    personData.append("2 PLAC Ligota-Panewniki (Katowice)\n");
    personData.append("2 ADDR Bazylika oo. Franciszkanów\n");
    personData.append("1 OBJE @M8@\n");
    personData.append("1 CHAN\n");
    personData.append("2 DATE 05 JUL 2016\n");
    personData.append("3 TIME 20:18:31\n");
    personData.append("2 _WT_USER pawel\n");
    personData.append("1 OBJE @M47@\n");
    personDataI181 = personData.toString();
    
    memGedcomDb = new HashMap<String, String>();
    memGedcomDb.put("@I2@", personDataI2);
    memGedcomDb.put("@I6@", personDataI6);
    memGedcomDb.put("@I181@", personDataI181);
    memGedcomDb.put("@F1@", familyDataF1);
    memGedcomDb.put("@F3@", familyDataF3);

  }

  @BeforeMethod
  public void setupBeforeMethod() {
    gedcomParser = new GedcomParser();
    System.out.println("*** Gedcom4jTest: Before method ***");
  }
  
  @AfterMethod
  public void setupAfterMethod() {
    System.out.println("^^^ Gedcom4jTest: After method ^^^");
  }

  @Test
  public void testFamilyParser() throws GedcomParserException, IOException {
    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(familyDataF1.getBytes("UTF-8")));
    Assert.assertNotNull(gedcomParser);
    gedcomParser.load(bis);
    for (String warning : gedcomParser.getWarnings())
      System.out.println(warning);
    for (String error : gedcomParser.getErrors())
      System.err.println(error);
    Gedcom gedcom = gedcomParser.getGedcom();
    Assert.assertNotNull(gedcom);
    Assert.assertNotNull(gedcom.getFamilies());
    Assert.assertEquals(gedcom.getFamilies().size(), 1);
    for (String familyName : gedcom.getFamilies().keySet()) {
      System.out.println("Familia: " + familyName);
      Family family = gedcom.getFamilies().get(familyName);
      Assert.assertNotNull(family);
      System.out.println(family);
      Assert.assertEquals(family.getXref(), "@F1@");
      Assert.assertNotNull(family.getHusband());
      Assert.assertNotNull(family.getWife());
      Assert.assertNotNull(family.getEvents());
      Assert.assertTrue(family.getEvents().size() > 0);
      Assert.assertTrue(family.getEvents().get(0).getType().equals(FamilyEventType.MARRIAGE));
    }
  }

  @Test
  public void testIndividualParser() throws IOException, GedcomParserException {
    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(personDataI2.getBytes("UTF-8")));
    Assert.assertNotNull(gedcomParser);
    gedcomParser.load(bis);
    for (String warning : gedcomParser.getWarnings())
      System.out.println(warning);
    for (String error : gedcomParser.getErrors())
      System.err.println(error);
    Gedcom gedcom = gedcomParser.getGedcom();
    Assert.assertNotNull(gedcom);
    Assert.assertNotNull(gedcom.getIndividuals());
    Assert.assertEquals(gedcom.getIndividuals().size(), 1);
    for (String individualName : gedcom.getIndividuals().keySet()) {
      System.out.println("Individual: " + individualName);
      Individual individual = gedcom.getIndividuals().get(individualName);
      Assert.assertNotNull(individual);
      System.out.println(individual);
      Assert.assertEquals(individual.getXref(), "@I2@");
      Assert.assertNotNull(individual.getNames());
      Assert.assertTrue(individual.getNames().size() == 1);
      Assert.assertNotNull(individual.getNames().get(0));
      System.out.println(individual.getNames().get(0).getBasic());
      Assert.assertTrue(individual.getNames().get(0).getSurname().getValue().equals("Niewiadomski"));
      Assert.assertNotNull(individual.getEvents());
      Assert.assertTrue(individual.getEvents().size() > 0);
      Assert.assertTrue(individual.getEvents().get(0).getType().equals(IndividualEventType.BIRTH));
      Assert.assertNotNull(individual.getEvents().get(0).getPlace());
      Assert.assertTrue(individual.getEvents().get(0).getPlace().getPlaceName().equals("Katowice"));
    }
  }

  @Test(dependsOnMethods={"testIndividualParser","testFamilyParser"})
  public void testDefaultFamily() throws IOException, GedcomParserException {
    String defaultFamilyId = "@F3@";
    Assert.assertNotNull(gedcomParser);
    BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(memGedcomDb.get(defaultFamilyId).getBytes("UTF-8")));
    gedcomParser.load(bis);
    String husbandId = gedcomParser.getGedcom().getFamilies().get(defaultFamilyId).getHusband().getXref();    
    bis = new BufferedInputStream(new ByteArrayInputStream(memGedcomDb.get(husbandId).getBytes("UTF-8")));    
    gedcomParser.load(bis);
    String wifeId = gedcomParser.getGedcom().getFamilies().get(defaultFamilyId).getWife().getXref();    
    bis = new BufferedInputStream(new ByteArrayInputStream(memGedcomDb.get(wifeId).getBytes("UTF-8")));    
    gedcomParser.load(bis);
    String childId = gedcomParser.getGedcom().getFamilies().get(defaultFamilyId).getChildren().get(0).getXref();    
    bis = new BufferedInputStream(new ByteArrayInputStream(memGedcomDb.get(childId).getBytes("UTF-8")));    
    gedcomParser.load(bis);
    System.out.println(gedcomParser.getGedcom());
  }
}
