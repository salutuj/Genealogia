package eu.pawelniewiadomski.java.spring.genealogia.model.gedcom;

import eu.pawelniewiadomski.java.spring.genealogia.model.AbstractModel;

public class GedcomIndividualModel extends AbstractModel{

/* Sample
 * i_id: 'I2';
 * i_file: '1';
 * i_rin: 'I2';
 * i_sex: 'M';
 * i_gedcom: 
 *   '0 @I2@ INDI\n
 *    1 NAME Paweł Henryk Piotr /Niewiadomski/\n
 *    2 GIVN Paweł Henryk Piotr\n
 *    2 SURN Niewiadomski\n
 *    2 NICK salutuj\n
 *    2 _AKA /afro/\n
 *    1 SEX M\n
 *    1 BIRT\n
 *    2 DATE 07 OCT 1983\n
 *    2 PLAC Katowice\n
 *    3 MAP
 *    4 LATI 50.123456
 *    4 LONG 19.123456
 *    1 FAMC @F1@\n
 *    1 FAMS @F3@\n
 *    1 CHAN\n
 *    2 DATE 03 MAR 2014\n
 *    3 TIME 20:08:07\n
 *    2 _WT_USER pawel\n
 *    1 OBJE @M1@'
*/
  
  private String id;
  private int file;
  private String rin;
  private String sex;
  private String gedcom;
  
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
   * @return the rin
   */
  public String getRin() {
    return rin;
  }

  /**
   * @param rin the rin to set
   */
  public void setRin(String rin) {
    this.rin = rin;
  }

  /**
   * @return the sex
   */
  public String getSex() {
    return sex;
  }

  /**
   * @param string the sex to set
   */
  public void setSex(String sex) {
    this.sex = sex;
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
  
}
