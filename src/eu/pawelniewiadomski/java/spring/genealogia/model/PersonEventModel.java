package eu.pawelniewiadomski.java.spring.genealogia.model;

import java.util.Date;

public class PersonEventModel extends AbstractModel {

  private String personId;
  private double gpsLat;
  private double gpsLong;
  private Date eventStartDate;
  private Date eventStopDate;
  private Object eventCustomData;
  /**
   * @return the personId
   */
  public String getPersonId() {
    return personId;
  }
  /**
   * @param personId the personId to set
   */
  public void setPersonId(String personId) {
    this.personId = personId;
  }
  /**
   * @return the gpsLat
   */
  public double getGpsLat() {
    return gpsLat;
  }
  /**
   * @param gpsLat the gpsLat to set
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
   * @param gpsLong the gpsLong to set
   */
  public void setGpsLong(double gpsLong) {
    this.gpsLong = gpsLong;
  }
  /**
   * @return the eventStartDate
   */
  public Date getEventStartDate() {
    return eventStartDate;
  }
  /**
   * @param eventStartDate the eventStartDate to set
   */
  public void setEventStartDate(Date eventStartDate) {
    this.eventStartDate = eventStartDate;
  }
  /**
   * @return the eventStopDate
   */
  public Date getEventStopDate() {
    return eventStopDate;
  }
  /**
   * @param eventStopDate the eventStopDate to set
   */
  public void setEventStopDate(Date eventStopDate) {
    this.eventStopDate = eventStopDate;
  }
  /**
   * @return the eventCustomData
   */
  public Object getEventCustomData() {
    return eventCustomData;
  }
  /**
   * @param eventCustomData the eventCustomData to set
   */
  public void setEventCustomData(Object eventCustomData) {
    this.eventCustomData = eventCustomData;
  }
}
