# CREATE DATABASE springdb;
USE springdb;

CREATE TABLE manufacturers (
  id   BINARY(16)   NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE products (
  id              BINARY(16)   NOT NULL PRIMARY KEY,
  name            VARCHAR(100) NOT NULL,
  price           BIGINT,
  manufacturer_id BINARY(16)   NOT NULL,
  FOREIGN KEY (manufacturer_id) REFERENCES manufacturers (id)

);

CREATE TABLE roles (
  id   BINARY(16)   NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE users (
  id         BINARY(16)   NOT NULL PRIMARY KEY,
  email      VARCHAR(100) NOT NULL,
  password   VARCHAR(100) NOT NULL,
  first_name VARCHAR(100),
  last_name  VARCHAR(100),
  roles_id   BINARY(16)   NOT NULL,
  FOREIGN KEY (roles_id) REFERENCES roles (id)
);

INSERT INTO manufacturers VALUE (1, 'Kordek');
INSERT INTO manufacturers VALUE (2, 'Agro');
INSERT INTO manufacturers VALUE (3, 'Mitprod');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO users
# VALUES (1, 'admin@gmail.com', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'Ivan', 'Ivanov', 2);
VALUES (1, 'admin@gmail.com', 111, 'Ivan', 'Ivanov', 2);