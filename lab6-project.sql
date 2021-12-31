create database springboot;
use springboot;


create table employees(
id int(11) not null auto_increment,
firstname varchar(45) not null,
lastname varchar(45) not null,
email varchar(45) not null,
primary key(id)
);