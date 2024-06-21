CREATE DATABASE atm;
USE atm;
CREATE TABLE users(id int primary key auto_increment, card varchar(16), pin varchar(4), uname varchar(25), bal int);
CREATE TABLE transactions(transid int primary key auto_increment, id int, amount int, stat varchar(3), bal int);
INSERT INTO users VALUES(1, "admin", "1234", "admin", 0);