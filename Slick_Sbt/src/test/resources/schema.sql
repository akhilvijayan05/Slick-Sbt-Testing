DROP TABLE IF EXISTS dependent;

CREATE TABLE IF NOT EXISTS dependent(emp_id int,name varchar(20),relation varchar(20),age int NULLABLE ,emp_id int PRIMARY KEY(emp_id),
                                                                                                                   FOREIGN KEY(id) REFERENCES employee(id)
);