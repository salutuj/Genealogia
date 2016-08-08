package eu.pawelniewiadomski.java.spring.genealogia.dao.impl;

import java.util.List;
import java.util.Map;

import eu.pawelniewiadomski.java.spring.genealogia.dao.FamilyDao;
import eu.pawelniewiadomski.java.spring.genealogia.model.FamilyModel;

public class DefaultFamilyDao implements FamilyDao{

  /*
   * pawel
   * @see eu.pawelniewiadomski.java.spring.genealogia.dao.GenericDao#find()
   * 
   * f_id, f_file, f_husb, f_wife, f_gedcom,          ...f_numchil
   * 'F1',    '1',   'I4',   'I9', '0 @F1@ FAM\n1 CHIL @I2@\n1 HUSB @I4@\n1 MARR\n2 TYPE Religious\n2 DATE 11 JUL 1981\n2 PLAC Katowice\n1 WIFE @I9@\n1 CHIL @I10@\n1 CHAN\n2 DATE 21 APR 2014\n3 TIME 21:38:12\n2 _WT_USER pawel ...', '2'
   * 'F3',    '1',   'I2',   'I6', '0 @F3@ FAM\n1 WIFE @I6@\n1 HUSB @I2@\n1 MARR\n2 DATE 15 AUG 2012\n2 PLAC Kęty\n1 CHAN\n2 DATE 25 JAN 2015\n3 TIME 00:02:42\n2 _WT_USER pawel\n1 CHIL @I181@', '1'
   * 
   * 
   * 
   * 
  

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

}
