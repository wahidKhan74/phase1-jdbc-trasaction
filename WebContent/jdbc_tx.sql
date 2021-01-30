use transaction_management;

CREATE TABLE transaction_management.employee (
  empId int(11) unsigned NOT NULL,
  name varchar(20) DEFAULT NULL,
  PRIMARY KEY (`empId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE transaction_management.address (
  empId int(11) unsigned NOT NULL,
  address varchar(20) DEFAULT NULL,
  city varchar(5) DEFAULT NULL,
  country varchar(20) DEFAULT NULL,
  PRIMARY KEY (`empId`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
