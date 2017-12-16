create database if not exists homework_1_1 default character set utf8;
use homework_1_1;

create table if not exists companies (
  id int not null auto_increment,
  name varchar(25) not null,
  primary key(id)
);

create table if not exists customers (
  id int not null auto_increment,
  name varchar(30) not null,
  primary key(id)
);

create table if not exists projects (
  id int not null auto_increment primary key,
  name varchar(30) not null,
  company_id int not null,
  customer_id int not null,
  foreign key (company_id) references companies (id),
  foreign key (customer_id) references customers (id)
);

create table if not exists developers (
  id int not null auto_increment,
  name varchar(30) not null,
  company_id int not null,
  project_id int not null,
  primary key (id),
  foreign key (company_id) references companies (id),
  foreign key (project_id) references projects (id)
);

create table if not exists skills (
  id int not null auto_increment,
  name varchar(25) not null,
  primary key (id)
);

create table if not exists developers_skills (
  developer_id int not null,
  skill_id int not null,
  primary key (developer_id, skill_id),
  foreign key (developer_id) references developers (id),
  foreign key (skill_id) references skills (id)
);