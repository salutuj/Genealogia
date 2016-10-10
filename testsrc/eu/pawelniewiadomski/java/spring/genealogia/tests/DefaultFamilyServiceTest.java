package eu.pawelniewiadomski.java.spring.genealogia.tests;

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
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.services.impl.DefaultFamilyService;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:genealogia-test-config.xml"})
public class DefaultFamilyServiceTest extends BaseTest  {

  @Mock
  private FamilyDao familyDao;

  @Mock
  private PersonService personService;
  
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
    GedcomFamilyModel defaultGedcomFamily = new GedcomFamilyModel();
    defaultGedcomFamily.setId("F3");
    defaultGedcomFamily.setFile(1);
    defaultGedcomFamily.setHusbandId("I2");
    defaultGedcomFamily.setWifeId("I6");
    defaultGedcomFamily.setGedcom("0 @F3@ FAM\n1 WIFE @I6@\n1 HUSB @I2@\n1 MARR\n2 DATE 15 AUG 2012\n2 PLAC Kęty\n1 CHAN\n2 DATE 25 JAN 2015\n3 TIME 00:02:42\n2 _WT_USER pawel\n1 CHIL @I181@");
    defaultGedcomFamily.setNumberOfChildren(1);    
    Mockito.when(familyDao.findFamilyById("F1")).thenReturn(defaultGedcomFamily);    
    PersonModel father = new PersonModel();
    father.setId("I2");
    father.setFirstName("Paweł");
    father.setLastName("Niewiadomski");
    father.setPlaceOfBirth("Katowice");
    father.setAge(33);    
    Mockito.when(personService.findPersonById(defaultGedcomFamily.getHusbandId())).thenReturn(father);
    PersonModel mother = new PersonModel();
    mother.setId("I6");
    mother.setFirstName("Magdalena");
    mother.setLastName("Zelewska");
    mother.setPlaceOfBirth("Oświęcim");
    mother.setAge(31);    
    Mockito.when(personService.findPersonById(defaultGedcomFamily.getWifeId())).thenReturn(mother);
    PersonModel daughter = new PersonModel();
    daughter.setId("I181");
    daughter.setFirstName("Maria");
    daughter.setLastName("Niewiadomska");
    daughter.setPlaceOfBirth("Katowice");
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
    Assert.assertEquals("F3", defaultFamily.getId());
    PersonModel father = defaultFamily.getFather();
    Assert.assertNotNull(father);
    Assert.assertEquals("I2", father.getId());
    Assert.assertEquals("Paweł", father.getFirstName());
    Assert.assertEquals("Niewiadomski", father.getLastName());
    Assert.assertEquals("Katowice", father.getPlaceOfBirth());
    Assert.assertEquals(33, father.getAge());
    PersonModel mother = defaultFamily.getMother();
    Assert.assertNotNull(mother);
    Assert.assertEquals("I6", mother.getId());
    Assert.assertEquals("Magdalena", mother.getFirstName());
    Assert.assertEquals("Zelewska", mother.getLastName());
    Assert.assertEquals("Oświęcim", mother.getPlaceOfBirth());
    Assert.assertNotNull(defaultFamily.getChildren());
    Assert.assertEquals(1, defaultFamily.getChildren().size());
    PersonModel daugther = defaultFamily.getChildren().iterator().next();    
    Assert.assertEquals("I181", daugther.getId());
    Assert.assertEquals("Maria", daugther.getFirstName());
    Assert.assertEquals("Niewiadomska", daugther.getLastName());
    Assert.assertEquals("Katowice", daugther.getPlaceOfBirth());
    //Assert.assertNotNull();
    //Assert.assertEquals("", defaultFamily.);       
  }


  
  
}
