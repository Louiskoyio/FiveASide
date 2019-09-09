SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 age NUMERIC,
 special_power VARCHAR,
 weakness VARCHAR,
 squad_id NUMERIC,
 overall_rating NUMERIC
 );

CREATE TABLE IF NOT EXISTS squads (
  squad_id int PRIMARY KEY auto_increment,
  name VARCHAR,
  objective VARCHAR
);




/*
INSERT INTO heroes (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('The Flash',46, 'Speed','bananas',1,80);
INSERT INTO heroes (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Oliver "Green Arrow" Queen',30, 'Arrows','Mirakuru Soldiers',1,87);
INSERT INTO squads (name, objective) VALUES ('Messi-Griezmann-Suarez', 'Deliver the UEFA Champions League');
INSERT INTO squads (name, objective) VALUES ('Becker-Matip-Virgil', 'Defend the UEFA Champions League');*/

