package eu.pawelniewiadomski.java.spring.genealogia.tests;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.gedcom4j.model.Family;
import org.gedcom4j.parser.GedcomParser;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import eu.pawelniewiadomski.java.spring.genealogia.services.impl.DefaultGedcomService;

@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:genealogia-test-config.xml" })
public class DefaultGedcomServiceTest extends BaseTest {
  
  @InjectMocks
  private DefaultGedcomService gedcomService;

  @Override
  @BeforeClass
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
  }

  @BeforeClass(dependsOnMethods = { "initMocks" })
  @Parameters("sampleGedcomFile")
  public void setupBeforeClass(@Optional("/gedcom/niewiadomski-sample.ged") String sampleFile) throws IOException {
    System.out.println("*** DefaultGedcomServiceTest.setupBeforeClass ***");
    gedcomParser = new GedcomParser();
    gedcomService.setGedcomParser(gedcomParser);
    Assert.assertEquals(gedcomService.getGedcomParser(), gedcomParser);
    BufferedInputStream sampleGedcomStream = new BufferedInputStream(getClass().getResourceAsStream(sampleFile));
    Assert.assertNotNull(sampleGedcomStream, "sampleFile is not valid");
    gedcomService.parseGedcomAsInputStream(sampleGedcomStream);
  }

  @Test
  public void testGedcomService() {
    System.out.println("*** DefaultGedcomServiceTest.testGedcomService ***");
    Family family = gedcomService.getFamilyById("F3");
    Assert.assertNotNull(family, "no family F3 found while there should be");
    Assert.assertNotNull(gedcomService.getIndividualById("I2"), "no individual I2 found while there should be");
    Assert.assertNotNull(gedcomService.getIndividualById("I6"), "no individual I6 found while there should be");
    Assert.assertNotNull(gedcomService.getIndividualById("I181"), "no individual I181 found while there should be");
    Assert.assertNotNull(family.getHusband(), "Family F3 should contain a husband");
    Assert.assertNotNull(family.getWife(), "Family F3 should contain a wife");
    Assert.assertNotNull(family.getChildren(), "Family F3 should contain a child");
    Assert.assertEquals(family.getHusband().getXref().equals("@I2@"), true);
    Assert.assertEquals(family.getWife().getXref().equals("@I6@"), true);
    Assert.assertEquals(family.getChildren().get(0).getXref().equals("@I181@"), true);
  }

}
