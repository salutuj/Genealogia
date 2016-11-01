package eu.pawelniewiadomski.java.spring.genealogia.model;

import java.util.Date;

public class PersonEventModel extends AbstractModel {

  public enum EventType {
    BIRTH,
    BAPTISM,
    MARRIAGE,
    DEATH
  }
  private EventType type;
  private String personId;
  private PlaceModel place; 
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
  /**
   * @return the place
   */
  public PlaceModel getPlace() {
    return place;
  }
  /**
   * @param place the place to set
   */
  public void setPlace(PlaceModel place) {
    this.place = place;
  }
  /**
   * @return the type
   */
  public EventType getType() {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(EventType type) {
    this.type = type;
  }
}
