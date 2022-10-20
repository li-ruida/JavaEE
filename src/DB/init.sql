

create database j2ee;
use j2ee;

create table info(
    id varchar(20),
    name varchar(10),
    gender varchar(10),
    iclass varchar(10),
    mobile varchar(20),
    email varchar(20),
    primary key (id)
);
drop table info;
create table userpassword(
    id varchar(20),
    password varchar(20) default '123456',
    img varchar(20),
    primary key (id)
);
drop table userpassword;






