package eu.pawelniewiadomski.java.spring.genealogia.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import eu.pawelniewiadomski.java.spring.genealogia.dao.IndividualDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;

public class DefaultIndividualDao extends NamedParameterJdbcDaoSupport implements IndividualDao {

/* Sample
 * i_id: 'I2';
 * i_file: '1';
 * i_rin: 'I2';
 * i_sex: 'M';
 * i_gedcom: '0 @I2@ INDI\n1 NAME Paweł Henryk Piotr /Niewiadomski/\n2 GIVN Paweł Henryk Piotr\n2 SURN Niewiadomski\n2 NICK salutuj\n2 _AKA /afro/\n1 SEX M\n1 BIRT\n2 DATE 07 OCT 1983\n2 PLAC Katowice\n1 FAMC @F1@\n1 FAMS @F3@\n1 CHAN\n2 DATE 03 MAR 2014\n3 TIME 20:08:07\n2 _WT_USER pawel\n1 OBJE @M1@'
 *   */
  private static final String INDIVIDUAL_QUERY = "" +
      "SELECT i_id, i_file, i_rin, i_sex, i_gedcom " +
      "FROM wt_individuals " +
      "WHERE i_id=?";
  
  private static final String INDIVIDUALEVENT_INSERT = "" +
      "INSERT INTO g_individualevents (personid, gpslat, gpslong, startdate, enddate, customdata) " +
      "VALUES (:personId, :gpsLat, :gpsLong, :startDate, :endDate, :customData )";      
  
  private static final String INDIVIDUALEVENT_QUERY = "" +
      "SELECT * " +
      "FROM g_individualevents " + 
      "WHERE id = :id";
  
  private static final String INDIVIDUALEVENT_UPDATE = "" +
      "UPDATE g_individualevents " +
      "SET personid=:personId, gpslat==:gpsLat, gpslong=:gpsLong, customdata=:customData" +
      "WHERE id=:id";

  private static final String INDIVIDUALEVENT_DELETE = "" +
      "DELETE FROM g_individualevents " +      
      "WHERE id=:id";
  
  /** Logger for this class and subclasses */
  protected final Log LOG = LogFactory.getLog(getClass());
  
  @Override
  public List<GedcomIndividualModel> find() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<GedcomIndividualModel> find(Map<String, ? extends Object> params) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Collection<GedcomIndividualModel> findIndividuals() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public GedcomIndividualModel findIndividualById(String id) {
    List<GedcomIndividualModel> individuals = getJdbcTemplate().query(INDIVIDUAL_QUERY, new Object[] {
        id },
        new RowMapper<GedcomIndividualModel>() {
          @Override
          public GedcomIndividualModel mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
            GedcomIndividualModel individual = new GedcomIndividualModel();
            individual.setId((rs.getString(1)));
            individual.setFile(Integer.valueOf(rs.getInt(2)));
            individual.setRin(rs.getString(3));
            individual.setSex(rs.getString(4));
            individual.setGedcom(rs.getString(5));
            return individual;
          }
        });
    return (GedcomIndividualModel) (individuals.size() > 0 ? individuals.get(0) : null);
  }

  @Override
  public GedcomIndividualModel findIndividualByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }
/*
  public void saveIndividualEvent(IndividualEventModel individualEventModel) {
    LOG.info("sessionFactory is " + getJdbcTemplate().toString() );
    getJdbcTemplate().update(MOTORIST_INSERT, new Object[] {
        motorist.getEmail(), motorist.getPassword(), motorist.getFirstName(), motorist.getLastName()
    });
  }
  public void updatePersonEvent(PersonEventModel personEventModel) {
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("email", motorist.getEmail());
    parameters.put("password", motorist.getPassword());
    parameters.put("firstName", motorist.getFirstName());
    parameters.put("lastName", motorist.getLastName());
    parameters.put("id", motorist.getId());
    getNamedParameterJdbcTemplate().update(MOTORIST_UPDATE, parameters);
    getJdbcTemplate().update(, new Object[] {
        motorist.getEmail(), motorist.getPassword(), motorist.getFirstName(), motorist.getLastName()
    });
  }
  
  public void updateMotorist(Motorist motorist) {
   
  }*/
}
