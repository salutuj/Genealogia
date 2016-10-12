package eu.pawelniewiadomski.java.spring.genealogia.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomFamilyModel;
import eu.pawelniewiadomski.java.spring.genealogia.model.gedcom.GedcomIndividualModel;


public class DefaultFamilyDao extends NamedParameterJdbcDaoSupport implements FamilyDao{

  /*
   * EXAMPLE OF A ROW: 
   * f_id, f_file, f_husb, f_wife, f_gedcom,          ...f_numchil
   * 'F1',    '1',   'I4',   'I9', '0 @F1@ FAM\n1 CHIL @I2@\n1 HUSB @I4@\n1 MARR\n2 TYPE Religious\n2 DATE 11 JUL 1981\n2 PLAC Katowice\n1 WIFE @I9@\n1 CHIL @I10@\n1 CHAN\n2 DATE 21 APR 2014\n3 TIME 21:38:12\n2 _WT_USER pawel ...', '2'
   * 'F3',    '1',   'I2',   'I6', '0 @F3@ FAM\n1 WIFE @I6@\n1 HUSB @I2@\n1 MARR\n2 DATE 15 AUG 2012\n2 PLAC Kęty\n1 CHAN\n2 DATE 25 JAN 2015\n3 TIME 00:02:42\n2 _WT_USER pawel\n1 CHIL @I181@', '1'

   */
  
  private static final String FAMILY_QUERY = "" +
      "SELECT f_id, f_file, f_husb, f_wife, f_gedcom, f_numchil " +
      "FROM wt_families " +
      "WHERE f_id=?";
  
  /*
   * pawel
   * @see eu.pawelniewiadomski.java.spring.genealogia.dao.GenericDao#find()
   */
	@Override
	public List<FamilyModel> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FamilyModel> find(Map<String, ? extends Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
  public GedcomFamilyModel findFamilyById(final String id) {
	   List<?> families = getJdbcTemplate().query(FAMILY_QUERY, new Object[] {
        id },
        new RowMapper<GedcomFamilyModel>() {
          @Override
          public GedcomFamilyModel mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
            GedcomFamilyModel family = new GedcomFamilyModel();
            family.setId((rs.getString(1)));
            family.setFile(Integer.valueOf(rs.getInt(2)));
            family.setHusbandId(rs.getString(3));
            family.setWifeId(rs.getString(4));
            family.setGedcom(rs.getString(5));
            family.setNumberOfChildren(rs.getInt(6));
            return family;
          }
        });
    return (GedcomFamilyModel) (families.size() > 0 ? families.get(0) : null); 
	}
}
