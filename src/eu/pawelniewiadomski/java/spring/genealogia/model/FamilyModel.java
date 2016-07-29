package eu.pawelniewiadomski.java.spring.genealogia.model;

import java.util.Collection;

public class FamilyModel extends AbstractModel {

  private String id;
  private String familyName;
  private PersonModel father;
  private PersonModel mother;
  private Collection<PersonModel> children;

  public String getId() {
    return id;
  }

  public String getFamilyName() {
    return familyName;
  }

  public PersonModel getFather() {
    return father;
  }

  public PersonModel getMother() {
    return mother;
  }

  public Collection<PersonModel> getChildren() {
    return children;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setFather(PersonModel father) {
    this.father = father;
  }

  public void setMother(PersonModel mother) {
    this.mother = mother;
  }

  public void setChildren(Collection<PersonModel> children) {
    this.children = children;
  }
}
