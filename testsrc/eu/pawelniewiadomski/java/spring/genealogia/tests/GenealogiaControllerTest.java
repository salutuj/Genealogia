package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.genealogia.controllers.GenealogiaController;
import eu.pawelniewiadomski.java.spring.genealogia.converters.AbstractConverter;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.FamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.PersonService;
import eu.pawelniewiadomski.java.spring.genealogia.utils.GedcomDateConverter;

public class GenealogiaControllerTest extends BaseTest{

  @Mock
  private FamilyService familyService;

  @Mock
  private PersonService personService;

  
  private AbstractConverter<FamilyModel, String> familyConverter;

  
  private AbstractConverter<PersonModel, String> personConverter;
  
  @InjectMocks
  private GenealogiaController testSubject;
 
  @Override  
  @BeforeClass
  public void initMocks(){
    testSubject = new GenealogiaController();
    MockitoAnnotations.initMocks(this);
  }
  
  @Override
  @BeforeClass(dependsOnMethods={"initMocks"})
  public void setupClass(@Optional("resources/niewiadomski-sample.ged")  String sampleFile) throws IOException, GedcomParserException{
    super.setupClass(sampleFile);
    Family defaultGedcomFamily = gedcomParser.getGedcom().getFamilies().get(0);
    PersonModel father = new PersonModel();
    father.setId(defaultGedcomFamily.getHusband().getXref());
    father.setFirstName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    father.setMiddleName(defaultGedcomFamily.getHusband().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    father.setLastName(defaultGedcomFamily.getHusband().getNames().get(0).getSurname().getValue());
    father.setDateOfBirth(GedcomDateConverter.convertGedcomDate(defaultGedcomFamily.getHusband().getEvents().get(0).getDate().getValue()));
    father.setPlaceOfBirth(defaultGedcomFamily.getHusband().getEvents().get(0).getPlace().getPlaceName());
    PersonModel mother = new PersonModel();
    mother.setId(defaultGedcomFamily.getWife().getXref());
    mother.setFirstName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[0]);
    mother.setMiddleName(defaultGedcomFamily.getWife().getNames().get(0).getGivenName().getValue().split(" ")[1]);
    mother.setLastName(defaultGedcomFamily.getWife().getNames().get(0).getSurname().getValue());
    mother.setDateOfBirth(GedcomDateConverter.convertGedcomDate(defaultGedcomFamily.getWife().getEvents().get(0).getDate().getValue()));
    mother.setPlaceOfBirth(defaultGedcomFamily.getWife().getEvents().get(0).getPlace().getPlaceName());
    PersonModel child = new PersonModel();
    Individual gedcomChild = defaultGedcomFamily.getChildren().get(0);
    child.setId(gedcomChild.getXref());
    child.setFirstName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[0]);
    child.setMiddleName(gedcomChild.getNames().get(0).getGivenName().getValue().split(" ")[1]);
    child.setLastName(gedcomChild.getNames().get(0).getSurname().getValue());
    child.setDateOfBirth(GedcomDateConverter.convertGedcomDate(gedcomChild.getEvents().get(0).getDate().getValue()));
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
  }
  
  
   
 
  
  @AfterMethod
  public void resetMocks(){
   // Mockito.reset(familyService, personService);
  }
  
  
  @Test
  public void testDefautFamily(){
    String jsonDesiredResponse = "[json]";
    String defaultFamily = testSubject.getDefaultFamily();
    Assert.assertTrue(defaultFamily.equals(jsonDesiredResponse));
  }
  
  
}
