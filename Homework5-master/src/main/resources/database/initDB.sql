# CREATE DATABASE servletsdb;
USE servletsdb;

CREATE TABLE manufacturer (
  id   BINARY(16)  NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product (
  id              BINARY(16)  NOT NULL,
  name            VARCHAR(50) NOT NULL,
  price           BIGINT,
  manufacturer_id BINARY(16)  NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (manufacturer_id) REFERENCES manufacturer (id)
);