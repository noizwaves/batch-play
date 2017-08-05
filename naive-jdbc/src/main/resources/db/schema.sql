DROP SCHEMA IF EXISTS source;
CREATE SCHEMA source;

DROP TABLE IF EXISTS source.widget;
CREATE TABLE source.widget (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  unit_price NUMERIC(10,2)
);

DROP SCHEMA IF EXISTS destination;
CREATE SCHEMA destination;

DROP TABLE IF EXISTS destination.gadget;
CREATE TABLE destination.gadget (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  price VARCHAR
);
