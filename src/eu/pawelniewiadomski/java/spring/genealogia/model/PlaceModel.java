package eu.pawelniewiadomski.java.spring.genealogia.model;

public class PlaceModel extends AbstractModel{
  private String name;
  private double gpsLat;
  private double gpsLong;

  /**
   * @return the gpsLat
   */
  public double getGpsLat() {
    return gpsLat;
  }

  /**
   * @param gpsLat
   *          the gpsLat to set
   */
  public void setGpsLat(double gpsLat) {
    this.gpsLat = gpsLat;
  }

  /**
   * @return the gpsLong
   */
  public double getGpsLong() {
    return gpsLong;
  }

  /**
   * @param gpsLong
   *          the gpsLong to set
   */
  public void setGpsLong(double gpsLong) {
    this.gpsLong = gpsLong;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
