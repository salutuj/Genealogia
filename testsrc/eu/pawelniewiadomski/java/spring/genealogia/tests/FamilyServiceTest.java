package eu.pawelniewiadomski.java.spring.genealogia.tests;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = {"classpath:genealogia-test-config.xml"})
public class FamilyServiceTest extends AbstractTestNGSpringContextTests  {

}
