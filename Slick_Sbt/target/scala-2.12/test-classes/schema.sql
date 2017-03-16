DROP TABLE IF EXISTS dependent;
DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(id int PRIMARY KEY ,name varchar(20),experience double);

CREATE TABLE IF NOT EXISTS dependent(emp_id int ,name varchar(20),relation varchar(20),age int , FOREIGN KEY(emp_id) REFERENCES employee(id));

