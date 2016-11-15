package eu.pawelniewiadomski.java.spring.genealogia.tests;

import org.gedcom4j.model.Family;
import org.gedcom4j.model.Individual;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.PersonModel;
import eu.pawelniewiadomski.java.spring.genealogia.services.GedcomService;
import eu.pawelniewiadomski.java.spring.genealogia.services.impl.DefaultFamilyService;
import eu.pawelniewiadomski.java.spring.genealogia.services.impl.DefaultPersonService;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:genealogia-test-config.xml" })
public class DefaultPersonServiceTest extends BaseTest {

  @Mock
  private GedcomService gedcomService;

  @Mock
  private IndividualDao individualDao;
  
  @InjectMocks
  private DefaultPersonService personService;

  @Override
  @BeforeClass
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @BeforeClass(dependsOnMethods = { "initMocks" })
  @Parameters("sampleGedcomFile")
  public void setupBeforeClass(@Optional("/gedcom/niewiadomski-4level-ancestry.ged") String sampleFile) {
    System.out.println("*** DefaultPersonServiceTest.setupBeforeClass ***");
    parseGedcomFile(sampleFile);
    // mock gedcomService calls to obtain custom individual and custom family
    Mockito.doAnswer(new Answer<Individual>() {
      @Override
      public Individual answer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        return gedcomParser.getGedcom().getIndividuals().get(GedcomService.id2Xref(args[0].toString()));
      }
    }).when(gedcomService).getIndividualById(Mockito.anyString());
    Mockito.doAnswer(new Answer<Family>() {
      @Override
      public Family answer(InvocationOnMock invocation) {
        Object[] args = invocation.getArguments();
        return gedcomParser.getGedcom().getFamilies().get(GedcomService.id2Xref(args[0].toString()));
      }
    }).when(gedcomService).getFamilyById(Mockito.anyString());
    Mockito.when(individualDao.findIndividualById(Mockito.anyString())).thenReturn(null);
    DefaultFamilyService familyService = new DefaultFamilyService();
    familyService.setGedcomService(gedcomService);
    familyService.setPersonService(personService);
    personService.setFamilyService(familyService);
  }

  @BeforeMethod
  public void setupBeforeMethod() {

  }

  @AfterMethod
  public void setupAfterMethod() {
  }

  @AfterMethod
  public void resetMocks() {
    // Mockito.reset(familyService, personService);
  }

  @Test
  public void testFindPersonById() {
    System.out.println("*** DefaultPersonServiceTest.testFindPersonById ***");
    PersonModel father = personService.findPersonById("I2");
    Assert.assertNotNull(father);
    Assert.assertEquals(father.getFirstName(), "Paweł", "Father first name different than expected!");
    Assert.assertEquals(father.getLastName(), "Niewiadomski", "Father last name different than expected!");
    PersonModel mother = personService.findPersonById("I6");
    Assert.assertNotNull(mother);
    Assert.assertEquals(mother.getFirstName(), "Magdalena", "Mother first name different than expected!");
    Assert.assertEquals(mother.getLastName(), "Zelewska", "Mother last name different than expected!");
    PersonModel daughter = personService.findPersonById("I181");
    Assert.assertNotNull(daughter);
    Assert.assertEquals(daughter.getFirstName(), "Maria", "Daughter first name different than expected!");
    Assert.assertEquals(daughter.getLastName(), "Niewiadomska", "Daughter last name different than expected!");
  }

  @Test
  public void testFindPersonWithAncestors() {
    System.out.println("*** DefaultPersonServiceTest.testFindPersonWithAncestors ***");
    // find person with no ancestors
    System.out.println("0-level ancestry:");
    PersonModel daughter0 = personService.findPersonWithAncestors("I181", 0);
    Assert.assertNotNull(daughter0);
    Assert.assertEquals(daughter0.getFirstName(), "Maria", "Daughter Level 0 first name different than expected!");
    Assert.assertEquals(daughter0.getLastName(), "Niewiadomska", "Daughter Level 0 last name different than expected!");
    Assert.assertNull(daughter0.getFather(), "Daughter Level 0 father has been found while it was not supposed to be present!");
    Assert.assertNull(daughter0.getMother(), "Daughter Level 0 mother has been found while it was not supposed to be present!");
    printAncestors(daughter0, 0);
    System.out.println("1-level ancestry:");
    PersonModel daughter1 = personService.findPersonWithAncestors("I181", 1);
    Assert.assertNotNull(daughter1);
    Assert.assertEquals(daughter1.getFirstName(), "Maria", "Daughter Level 1 first name different than expected!");
    Assert.assertEquals(daughter1.getLastName(), "Niewiadomska", "Daughter Level 1 last name different than expected!");
    Assert.assertNotNull(daughter1.getFather(), "Daughter Level 1 father has not been found!");
    Assert.assertNotNull(daughter1.getMother(), "Daughter Level 1 mother has not been found!");
    Assert.assertEquals(daughter1.getFather().getFirstName(), "Paweł", "Daughter Level 1 - father name is not Paweł!");
    Assert.assertEquals(daughter1.getMother().getFirstName(), "Magdalena", "Daughter Level 1 - mother name is not Magdalena!");
    Assert.assertNull(daughter1.getFather().getFather(), "Daughter Level 1 - father of father is not null?");
    Assert.assertNull(daughter1.getFather().getMother(), "Daughter Level 1 - mother of father is not null?");
    Assert.assertNull(daughter1.getMother().getFather(), "Daughter Level 1 - father of mother is not null?");
    Assert.assertNull(daughter1.getMother().getMother(), "Daughter Level 1 - mother of mother is not null?");
    printAncestors(daughter1, 0);
    System.out.println("4-level ancestry:");
    PersonModel daughter4 = personService.findPersonWithAncestors("I181", 4);
    Assert.assertNotNull(daughter4);
    Assert.assertEquals(daughter4.getFirstName(), "Maria", "Daughter Level 4 first name different than expected!");
    Assert.assertEquals(daughter4.getLastName(), "Niewiadomska", "Daughter Level 4 last name different than expected!");
    Assert.assertNotNull(daughter4.getFather(), "Daughter Level 4 father has not been found!");
    Assert.assertNotNull(daughter4.getMother(), "Daughter Level 4 mother has not been found!");
    Assert.assertEquals(daughter4.getFather().getFirstName(), "Paweł", "Daughter Level 4 - father name is not Paweł!");
    Assert.assertEquals(daughter4.getMother().getFirstName(), "Magdalena", "Daughter Level 4 - mother name is not Magdalena!");
    printAncestors(daughter4, 0);
  }

  private void printAncestors(final PersonModel person, int level) {
    PersonModel father = person.getFather();
    String fatherId = father != null ? (father.getId() != null ? father.getId() : "<empty>") : "father is null";
    PersonModel mother = person.getMother();
    String intend = new String("");
    for (int i = 0; i < level; i++)
      intend += "  ";

    String motherId = mother != null ? (mother.getId() != null ? mother.getId() : "<empty>") : "mother is null";
    System.out.printf("%d:%sPerson: [id: \"%s\", firstName: \"%s\", lastName: \"%s\"]; fatherId: \"%s\", motherId: \"%s\"\n", level, intend,
        person.getId(), person.getFirstName(), person.getLastName(), fatherId, motherId);
    if (father != null) printAncestors(father, level + 1);
    if (mother != null) printAncestors(mother, level + 1);
  }

}
