DROP SCHEMA IF EXISTS source CASCADE ;
CREATE SCHEMA source;

DROP TABLE IF EXISTS source.widget;
CREATE TABLE source.widget (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  unit_price NUMERIC(10,2)
);

DROP TABLE IF EXISTS source.gizmo;
CREATE TABLE source.gizmo (
  id SERIAL PRIMARY KEY,
  creator_name VARCHAR,
  creator_type VARCHAR(2),
  cost INT
);

DROP SCHEMA IF EXISTS destination CASCADE;
CREATE SCHEMA destination;

DROP TABLE IF EXISTS destination.gadget;
CREATE TABLE destination.gadget (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  price VARCHAR
);
