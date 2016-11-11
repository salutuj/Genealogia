package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEvent;
import org.gedcom4j.model.IndividualEventType;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import eu.pawelniewiadomski.java.spring.genealogia.controllers.GenealogiaController;
import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.converters.json.JsonObjectOrArray;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonEventModel.EventType;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PlaceModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;


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
  private AbstractConverter<FamilyModel, JsonObjectOrArray> familyConverter;

  @Autowired  
  private AbstractConverter<FamilyModel, JsonObjectOrArray> realFamilyConverter;
  
  @Autowired
  private AbstractConverter<PersonModel, JsonObjectOrArray> realPersonConverter;
  
  @InjectMocks
  private GenealogiaController genealogiaController;
 
  @Override  
  @BeforeClass
  public void initMocks(){
    MockitoAnnotations.initMocks(this);
  }
    
  @BeforeClass(dependsOnMethods={"initMocks"})
  @Parameters("sampleGedcomFile")
  public void setupClass(@Optional("/gedcom/niewiadomski-sample.ged")  String sampleFile){    
    System.out.println("*** GenealogiaControllerTest.setupClass ***");
    parseGedcomFile(sampleFile);
    Family defaultGedcomFamily = gedcomParser.getGedcom().getFamilies().get("@F3@");
    Assert.assertNotNull(defaultGedcomFamily, "defaultGedcomFamily is null");
    PersonModel father = new PersonModel();
    father.setId(defaultGedcomFamily.getHusband().getXref());
    father.setFirstName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    father.setMiddleName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    father.setLastName(defaultGedcomFamily.getHusband().getNames().get(0).getSurname().getValue());
    father.setBirth(setupBirthEvent(defaultGedcomFamily.getHusband()));
    PersonModel mother = new PersonModel();
    mother.setId(defaultGedcomFamily.getWife().getXref());
    mother.setFirstName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    mother.setMiddleName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    mother.setLastName(defaultGedcomFamily.getWife().getNames().get(0).getSurname().getValue());
    mother.setBirth(setupBirthEvent(defaultGedcomFamily.getWife()));
    PersonModel child = new PersonModel();
    Individual gedcomChild = defaultGedcomFamily.getChildren().get(0);
    child.setId(gedcomChild.getXref());
    child.setFirstName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[0]);
    child.setMiddleName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[1]);
    child.setLastName(gedcomChild.getNames().get(0).getSurname().getValue());
    child.setBirth(setupBirthEvent(gedcomChild));    
    FamilyModel defaultFamilyModel = new FamilyModel();
    defaultFamilyModel.setId(defaultGedcomFamily.getXref());
    defaultFamilyModel.setFamilyName("Niewiadomscy");
    defaultFamilyModel.setFather(father);
    defaultFamilyModel.setMother(mother);
    ArrayList<PersonModel> children = new ArrayList<PersonModel>();
    children.add(child);
    defaultFamilyModel.setChildren(children);  
    // setup mocks
    Mockito.when(familyService.getDefaultFamily()).thenReturn(defaultFamilyModel);
    Mockito.when(familyConverter.convert(defaultFamilyModel)).thenReturn(realFamilyConverter.convert(defaultFamilyModel));
  }
  
  private PersonEventModel setupBirthEvent(Individual individual){
    PersonEventModel birthEvent = new PersonEventModel();
    for ( IndividualEvent event : individual.getEvents())
      if ( event.getType() == IndividualEventType.BIRTH ){                   
        birthEvent.setType(EventType.BIRTH);
        PlaceModel birthPlace = new PlaceModel();
        birthPlace.setName(event.getPlace().getPlaceName());
        birthPlace.setGpsLat(GedcomService.positionValue2Double(event.getPlace().getLatitude().getValue()));
        birthPlace.setGpsLong(GedcomService.positionValue2Double(event.getPlace().getLongitude().getValue()));
        birthEvent.setPlace(birthPlace);        
        birthEvent.setPersonId(GedcomService.xref2Id(individual.getXref()));
        birthEvent.setEventStartDate(GedcomService.convertGedcomDate(event.getDate().getValue()));                      
    }
    return birthEvent;
  }
   
 
  
  @AfterMethod
  public void resetMocks(){
   // Mockito.reset(familyService, personService);
  }
  
  
  @Test
  @Parameters("defaultFamilyJsonFilename")
  public void testDefautFamilyInController(@Optional("/json/defaultFamily.json")  String defaultFamilyFilename) throws IOException{        
    Gson gson = new Gson();
    String desiredResponse = inputStreamToString(new BufferedInputStream(getClass().getResourceAsStream(defaultFamilyFilename)));
    JsonElement desiredResponseAsJsonElement = gson.toJsonTree(desiredResponse);
    JsonElement actualResponseAsJsonElement = gson.toJsonTree(genealogiaController.getDefaultFamily());
    Assert.assertTrue(actualResponseAsJsonElement.getAsJsonObject().equals(desiredResponseAsJsonElement.getAsJsonObject()));
  }
  
  
}
