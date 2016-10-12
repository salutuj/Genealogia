CREATE TABLE wt_individuals (
  i_id varchar(5) NOT NULL PRIMARY KEY,
  i_file INTEGER NOT NULL,
  i_rin varchar(20) NOT NULL,
  i_sex CHAR NOT NULL,
  i_gedcom VARCHAR(1024) NOT NULL  
);

CREATE TABLE wt_families (
  f_id varchar(5) NOT NULL PRIMARY KEY,
  f_file INTEGER NOT NULL,
  f_husb varchar(5) DEFAULT NULL ,
  f_wife varchar(5) DEFAULT NULL,
  f_gedcom VARCHAR(1024) NOT NULL,
  f_numchil INTEGER NOT NULL,    
);
--CREATE INDEX products_description ON products(description);