drop keyspace IF EXISTS soubhagya1;

CREATE KEYSPACE soubhagya  
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE soubhagya.Employee(
	id int PRIMARY KEY ,
	firstName text,
	lastName text
);

SELECT * FROM soubhagya.Employee;
SELECT * FROM soubhagya.Employee WHERE id=1;

INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (1,'Ram','Singh');
INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (2,'Shyam','Sharma');
INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (3,'Rahul','Shekhar');
INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (4,'Santanu','Shetty');
INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (5,'Sri','Gaoud');
INSERT INTO soubhagya.Employee(id,firstName,lastName) VALUES (6,'Shanti','Niketan');
----------------------

CREATE TABLE soubhagya.USER(id int PRIMARY KEY,username text,password text,age int);
SELECT * FROM soubhagya.user;
INSERT INTO soubhagya.user (id, username, password, age) VALUES (1, 'Alexa', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 33);
INSERT INTO soubhagya.user (id, username, password, age) VALUES (2, 'Tom', '$2a$04$PCIX2hYrve38M7eOcqAbCO9UqjYg7gfFNpKsinAxh99nms9e.8HwK', 23);
INSERT INTO soubhagya.user (id, username, password, age) VALUES (3, 'Adam', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 45);