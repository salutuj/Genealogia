package eu.pawelniewiadomski.java.spring.genealogia.model;

import java.util.Collection;
import java.util.Date;

public class PersonModel extends AbstractModel {

  private String id;
  private String firstName;
  private String lastName;
  private int age;
  private Date dateOfBirth;
  private Date dateOfDeath;
  private String placeOfBirth;
  private String placeOfDeath;
  private String maidenName;
  private String middleName;  
  private String relationToParent;
  private Collection<PersonEventModel> events;

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }
  
  public String getMaidenName() {
    return maidenName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public Date getDateOfDeath() {
    return dateOfDeath;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public String getPlaceOfDeath() {
    return placeOfDeath;
  }
  
  public String getRelationToParent() {
    return relationToParent;
  }
 
  public void setId(String id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public void setMaidenName(String maidenName) {
    this.maidenName = maidenName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public void setDateOfDeath(Date dateOfDeath) {
    this.dateOfDeath = dateOfDeath;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public void setPlaceOfDeath(String placeOfDeath) {
    this.placeOfDeath = placeOfDeath;
  }
  
  public void setRelationToParent(String relationToParent) {
    this.relationToParent = relationToParent;
  }

  /**
   * @return the events
   */
  public Collection<PersonEventModel> getEvents() {
    return events;
  }

  /**
   * @param events the events to set
   */
  public void setEvents(Collection<PersonEventModel> events) {
    this.events = events;
  }

}
