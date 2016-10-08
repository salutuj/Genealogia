CREATE TABLE wt_individuals (
  i_id varchar(20) NOT NULL PRIMARY KEY,
  i_file INTEGER NOT NULL DEFAULT 1,
  i_rin varchar(20) NOT NULL,
  i_sex enum('U','M','F') NOT NULL,
  i_gedcom VARCHAR(255) NOT NULL,  
);

CREATE TABLE wt_families (
  f_id varchar(20) NOT NULL PRIMARY KEY,
  f_file INTEGER NOT NULL,
  f_husb varchar(20) DEFAULT NULL,
  f_wife varchar(20) DEFAULT NULL,
  f_gedcom VARCHAR(255) NOT NULL,
  f_numchil int(11) NOT NULL,
  FOREIGN KEY fk_husb (f_husb),
  FOREIGN KEY fk_wife (f_wife)
);
--CREATE INDEX products_description ON products(description);