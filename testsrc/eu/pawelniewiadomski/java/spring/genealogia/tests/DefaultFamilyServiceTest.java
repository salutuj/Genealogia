package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.parser.GedcomParser;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel.EventType;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PlaceModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.services.impl.DefaultFamilyService;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:genealogia-test-config.xml"})
public class DefaultFamilyServiceTest extends BaseTest  {

  @Mock
  private PersonService personService;
  
  @Mock
  private GedcomService gedcomService;
  
  @InjectMocks
  private DefaultFamilyService familyService;

  
  
  @Override
  @BeforeClass
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }
  
  @BeforeClass(dependsOnMethods={"initMocks"})
  public void setupBeforeClass() {
    System.out.println("*** DefaultFamilyServiceTest.setupBeforeClass ***");
    String defaulFamilyAsGedcom = "0 @F1@ FAM\n1 WIFE @I6@\n1 HUSB @I2@\n1 MARR\n2 DATE 15 AUG 2012\n2 PLAC Kęty\n1 CHAN\n2 DATE 25 JAN 2015\n3 TIME 00:02:42\n2 _WT_USER pawel\n1 CHIL @I181@";
    GedcomParser gedcomParser = new GedcomParser();
    BufferedInputStream bis;
    try {
      bis = new BufferedInputStream(new ByteArrayInputStream(defaulFamilyAsGedcom.getBytes("UTF-8")));
      gedcomParser.load(bis);
    } catch (UnsupportedEncodingException e1) {      
      e1.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    } catch (GedcomParserException e) {
      e.printStackTrace();
    }       
    Family family = gedcomParser.getGedcom().getFamilies().get("@F1@");    
    Mockito.when(gedcomService.getFamilyById("F1")).thenReturn(family);   
    PersonModel father = new PersonModel();
    father.setId("I2");
    father.setFirstName("Paweł");
    father.setLastName("Niewiadomski");
    PersonEventModel birthEvent = new PersonEventModel();
    birthEvent.setType(EventType.BIRTH);
    PlaceModel birthPlace = new PlaceModel();
    birthPlace.setName("Katowice"); 
    birthPlace.setGpsLat(50.231433);
    birthPlace.setGpsLong(18.983352);
    birthEvent.setPlace(birthPlace);
    birthEvent.setPersonId("I2");
    birthEvent.setEventStartDate(new Date(83,9,7));
    father.setBirth(birthEvent);
    father.setAge(33);    
    Mockito.when(personService.findPersonById(GedcomService.xref2Id(family.getHusband().getXref()))).thenReturn(father);
    PersonModel mother = new PersonModel();
    mother.setId("I6");
    mother.setFirstName("Magdalena");
    mother.setLastName("Zelewska");    
    birthEvent = new PersonEventModel();
    birthEvent.setType(EventType.BIRTH);
    birthPlace = new PlaceModel();
    birthPlace.setName("Oświęcim");    
    birthEvent.setPlace(birthPlace);
    birthEvent.setPersonId("I6");
    birthEvent.setEventStartDate(new Date(85,7,6));
    mother.setBirth(birthEvent);
    mother.setAge(31);    
    Mockito.when(personService.findPersonById(GedcomService.xref2Id(family.getWife().getXref()))).thenReturn(mother);
    PersonModel daughter = new PersonModel();
    daughter.setId("I181");
    daughter.setFirstName("Maria");
    daughter.setLastName("Niewiadomska");    
    birthEvent = new PersonEventModel();
    birthEvent.setType(EventType.BIRTH);
    birthPlace = new PlaceModel();
    birthPlace.setName("Katowice");    
    birthEvent.setPlace(birthPlace);
    birthEvent.setPersonId("I181");
    birthEvent.setEventStartDate(new Date(115,0,13));
    daughter.setBirth(birthEvent);
    daughter.setAge(1);    
    Mockito.when(personService.findPersonById("I181")).thenReturn(daughter);
  }

  
  @BeforeMethod
  public void setupBeforeMethod() {
        
  }
  
  @AfterMethod
  public void setupAfterMethod() {
  }

  @AfterMethod
  public void resetMocks() {    
    //Mockito.reset(familyService, personService);
  }
  
  @Test  
  public void testDefaultFamily(){   
    System.out.println("*** DefaultFamilyServiceTest.testDefaultFamily ***");
    FamilyModel defaultFamily = familyService.getDefaultFamily();
    Assert.assertNotNull(defaultFamily);
    //TODO Assert.assertEquals("Niewiadomscy", defaultFamily.getFamilyName());
    Assert.assertEquals("F1", defaultFamily.getId());
    PersonModel father = defaultFamily.getFather();
    Assert.assertNotNull(father);
    Assert.assertEquals("I2", father.getId());
    Assert.assertEquals("Paweł", father.getFirstName());
    Assert.assertEquals("Niewiadomski", father.getLastName());
    Assert.assertNotNull(father.getBirth());
    Assert.assertEquals("Katowice", father.getBirth().getPlace().getName());    
    Assert.assertEquals(50.231433, father.getBirth().getPlace().getGpsLat());
    Assert.assertEquals(18.983352, father.getBirth().getPlace().getGpsLong());
    Assert.assertEquals(33, father.getAge());
    PersonModel mother = defaultFamily.getMother();
    Assert.assertNotNull(mother);
    Assert.assertEquals("I6", mother.getId());
    Assert.assertEquals("Magdalena", mother.getFirstName());
    Assert.assertEquals("Zelewska", mother.getLastName());
    Assert.assertNotNull(mother.getBirth());
    Assert.assertEquals("Oświęcim", mother.getBirth().getPlace().getName());
    Assert.assertNotNull(defaultFamily.getChildren());
    Assert.assertEquals(1, defaultFamily.getChildren().size());
    PersonModel daughter = defaultFamily.getChildren().iterator().next();    
    Assert.assertEquals("I181", daughter.getId());
    Assert.assertEquals("Maria", daughter.getFirstName());
    Assert.assertEquals("Niewiadomska", daughter.getLastName());
    Assert.assertNotNull(daughter.getBirth());
    Assert.assertEquals("Katowice", daughter.getBirth().getPlace().getName());
    //Assert.assertNotNull();
    //Assert.assertEquals("", defaultFamily.);       
  }


  
  
}
