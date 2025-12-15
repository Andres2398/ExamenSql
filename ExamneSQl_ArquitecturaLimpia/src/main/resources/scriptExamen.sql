drop database if exists examen;
create database if not exists examen;

use examen;

create table if not exists tabla1(
id int not null auto_increment,
nombre1 text 
) engine = InnoDb;

create table if not exists tabla2(
id_tabla2 int not null auto_increment,
nombre2 text
) engine = InnoDB;

insert into tabla1(nombre1) values ("pepe");



 

