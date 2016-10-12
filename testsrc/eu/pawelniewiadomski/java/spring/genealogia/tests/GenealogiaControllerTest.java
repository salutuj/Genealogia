package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.genealogia.controllers.GenealogiaController;
import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomUtils;


@WebAppConfiguration
@ContextHierarchy({
  @ContextConfiguration(locations = {"classpath:genealogia-test-config.xml"})
})
public class GenealogiaControllerTest extends BaseTest{

  @Mock
  private FamilyService familyService;

  @Mock
  private PersonService personService;

  @Mock
  private AbstractConverter<FamilyModel, String> familyConverter;

  @Autowired  
  private AbstractConverter<FamilyModel, String> realFamilyConverter;
  
  @Autowired
  private AbstractConverter<PersonModel, String> realPersonConverter;
  
  @InjectMocks
  private GenealogiaController genealogiaController;
 
  @Override  
  @BeforeClass
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }
  
  @Override
  @BeforeClass(dependsOnMethods={"initMocks"})
  @Parameters("sampleGedcomFile")
  public void setupClass(@Optional("/gedcom/niewiadomski-sample.ged")  String sampleFile) throws IOException, GedcomParserException{
    super.setupClass(sampleFile);
    System.out.println("*** GenealogiaControllerTest.setupClass ***");
    Family defaultGedcomFamily = gedcomParser.getGedcom().getFamilies().get("@F3@");
    Assert.assertNotNull(defaultGedcomFamily, "defaultGedcomFamily is null");
    PersonModel father = new PersonModel();
    father.setId(defaultGedcomFamily.getHusband().getXref());
    father.setFirstName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    father.setMiddleName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    father.setLastName(defaultGedcomFamily.getHusband().getNames().get(0).getSurname().getValue());
    father.setDateOfBirth(GedcomUtils.convertGedcomDate(defaultGedcomFamily.getHusband().getEvents().get(0).getDate().getValue()));
    father.setPlaceOfBirth(defaultGedcomFamily.getHusband().getEvents().get(0).getPlace().getPlaceName());
    PersonModel mother = new PersonModel();
    mother.setId(defaultGedcomFamily.getWife().getXref());
    mother.setFirstName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    mother.setMiddleName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    mother.setLastName(defaultGedcomFamily.getWife().getNames().get(0).getSurname().getValue());
    mother.setDateOfBirth(GedcomUtils.convertGedcomDate(defaultGedcomFamily.getWife().getEvents().get(0).getDate().getValue()));
    mother.setPlaceOfBirth(defaultGedcomFamily.getWife().getEvents().get(0).getPlace().getPlaceName());
    PersonModel child = new PersonModel();
    Individual gedcomChild = defaultGedcomFamily.getChildren().get(0);
    child.setId(gedcomChild.getXref());
    child.setFirstName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[0]);
    child.setMiddleName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[1]);
    child.setLastName(gedcomChild.getNames().get(0).getSurname().getValue());
    child.setDateOfBirth(GedcomUtils.convertGedcomDate(gedcomChild.getEvents().get(0).getDate().getValue()));
    child.setPlaceOfBirth(gedcomChild.getEvents().get(0).getPlace().getPlaceName());
    FamilyModel defaultFamilyModel = new FamilyModel();
    defaultFamilyModel.setId(defaultGedcomFamily.getXref());
    defaultFamilyModel.setFamilyName("Niewiadomscy");
    defaultFamilyModel.setFather(father);
    defaultFamilyModel.setMother(mother);
    ArrayList<PersonModel> children = new ArrayList<PersonModel>();
    children.add(child);
    defaultFamilyModel.setChildren(children);    
    Mockito.when(familyService.getDefaultFamily()).thenReturn(defaultFamilyModel);
    Mockito.when(familyConverter.convert(defaultFamilyModel)).thenReturn(realFamilyConverter.convert(defaultFamilyModel));
  }
  
  
   
 
  
  @AfterMethod
  public void resetMocks(){
   // Mockito.reset(familyService, personService);
  }
  
  
  @Test
  public void testADefautFamily(){
    String jsonDesiredResponse = "{\"mother\": {\"firstName\": \"Magdalena\",\"lastName\": \"Zelewska\"," +
      "\"placeOfBirth\": \"Oświęcim\",\"personId\": \"@I6@\",\"dateOfBirth\": \"Wed Oct 24 00:00:00 CET 1906\"," +
      "\"age\": 0},\"father\": {\"firstName\": \"Paweł\",\"lastName\": \"Niewiadomski\"," +
      "\"placeOfBirth\": \"Katowice\",\"personId\": \"@I2@\",\"dateOfBirth\": \"Sun Dec 22 00:00:00 CET 1907\"," +
      "\"age\": 0},\"name\": \"Niewiadomscy\",\"id\": \"@F3@\"}";
    String defaultFamily = genealogiaController.getDefaultFamily();
    //Assert.assertTrue(defaultFamily.equals(jsonDesiredResponse));
  }
  
  
}
