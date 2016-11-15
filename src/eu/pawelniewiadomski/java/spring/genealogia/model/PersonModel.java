package eu.pawelniewiadomski.java.spring.genealogia.model;

import java.util.Collection;
import java.util.Date;

public class PersonModel extends AbstractModel {

  private String id;
  private String firstName;
  private String lastName;
  private int age;
  private String maidenName;
  private String middleName;
  private String relationToParent;
  private PersonEventModel birth;
  private PersonEventModel death;
  private Collection<PersonEventModel> events;
  private Collection<String> familiesAsChild;
  private Collection<String> familiesAsSpouse;
  private PersonModel father;
  private PersonModel mother;

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

  public String getRelationToParent() {
    return relationToParent;
  }

  /**
   * @return the events
   */
  public Collection<PersonEventModel> getEvents() {
    return events;
  }

  public Collection<String> getFamiliesAsChild() {
    return familiesAsChild;
  }

  public Collection<String> getFamiliesAsSpouse() {
    return familiesAsSpouse;
  }

  /**
   * @return the father
   */
  public PersonModel getFather() {
    return father;
  }

  /**
   * @return the mother
   */
  public PersonModel getMother() {
    return mother;
  }

  // setters

  public void setId(final String id) {
    this.id = id;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public void setMiddleName(final String middleName) {
    this.middleName = middleName;
  }

  public void setMaidenName(final String maidenName) {
    this.maidenName = maidenName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public void setRelationToParent(final String relationToParent) {
    this.relationToParent = relationToParent;
  }

  /**
   * @param events
   *          the events to set
   */
  public void setEvents(final Collection<PersonEventModel> events) {
    this.events = events;
  }

  public void setFamiliesAsChild(final Collection<String> familiesAsChild) {
    this.familiesAsChild = familiesAsChild;
  }

  public void setFamiliesAsSpouse(final Collection<String> familiesAsSpouse) {
    this.familiesAsSpouse = familiesAsSpouse;
  }

  /**
   * @param father
   *          the father to set
   */
  public void setFather(PersonModel father) {
    this.father = father;
  }

  /**
   * @param mother
   *          the mother to set
   */
  public void setMother(PersonModel mother) {
    this.mother = mother;
  }

  /**
   * @return the birth
   */
  public PersonEventModel getBirth() {
    return birth;
  }

  /**
   * @param birth the birth to set
   */
  public void setBirth(PersonEventModel birth) {
    this.birth = birth;
  }

  /**
   * @return the death
   */
  public PersonEventModel getDeath() {
    return death;
  }

  /**
   * @param death the death to set
   */
  public void setDeath(PersonEventModel death) {
    this.death = death;
  }

}
