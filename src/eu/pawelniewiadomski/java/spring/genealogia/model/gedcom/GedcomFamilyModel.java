package eu.pawelniewiadomski.java.spring.genealogia.model.gedcom;

public class GedcomFamilyModel {
  /* Sample Families
   * f_id, f_file, f_husb, f_wife, f_gedcom,          ...f_numchil
   * 'F1',    '1',   'I4',   'I9', '0 @F1@ FAM\n1 CHIL @I2@\n1 HUSB @I4@\n1 MARR\n2 TYPE Religious\n2 DATE 11 JUL 1981\n2 PLAC Katowice\n1 WIFE @I9@\n1 CHIL @I10@\n1 CHAN\n2 DATE 21 APR 2014\n3 TIME 21:38:12\n2 _WT_USER pawel ...', '2'
   * 'F3',    '1',   'I2',   'I6', '0 @F3@ FAM\n1 WIFE @I6@\n1 HUSB @I2@\n1 MARR\n2 DATE 15 AUG 2012\n2 PLAC Kęty\n1 CHAN\n2 DATE 25 JAN 2015\n3 TIME 00:02:42\n2 _WT_USER pawel\n1 CHIL @I181@', '1'
   */
  
  private String id;
  private int file;
  private String husbandId;
  private String wifeId;
  private String gedcom;
  private int numberOfChildren;
  
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }
  
  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the file
   */
  public int getFile() {
    return file;
  }

  /**
   * @param file the file to set
   */
  public void setFile(int file) {
    this.file = file;
  }

  /**
   * @return the husbandId
   */
  public String getHusbandId() {
    return husbandId;
  }

  /**
   * @param husbandId the husbandId to set
   */
  public void setHusbandId(String husbandId) {
    this.husbandId = husbandId;
  }

  /**
   * @return the wifeId
   */
  public String getWifeId() {
    return wifeId;
  }

  /**
   * @param wifeId the wifeId to set
   */
  public void setWifeId(String wifeId) {
    this.wifeId = wifeId;
  }

  /**
   * @return the gedcom
   */
  public String getGedcom() {
    return gedcom;
  }

  /**
   * @param gedcom the gedcom to set
   */
  public void setGedcom(String gedcom) {
    this.gedcom = gedcom;
  }

  /**
   * @return the numberOfChildren
   */
  public int getNumberOfChildren() {
    return numberOfChildren;
  }

  /**
   * @param numberOfChildren the numberOfChildren to set
   */
  public void setNumberOfChildren(int numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
  }
  
  
}
