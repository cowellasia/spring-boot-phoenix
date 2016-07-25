-- Insert into department
INSERT INTO departments VALUES (1, 'Developemt');;
INSERT INTO departments VALUES (2, 'HR');;

-- Insert into employees(with id)
INSERT INTO employees VALUES (1, 'Alex', 'alex@gmail.com', 1);;
INSERT INTO employees VALUES (2, 'Joy', 'joy@gmail.com', 1);;
INSERT INTO employees VALUES (3, 'Matt', 'matt@gmail.com', 2);;

-- Insert into employees(without id)
INSERT INTO employees(name, email, department_id) VALUES ('Matt', 'matt@gmail.com', 2);;