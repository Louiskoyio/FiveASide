SET MODE PostgreSQL;

SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS players (id int PRIMARY KEY auto_increment, name VARCHAR, age INTEGER, special_power VARCHAR, weakness VARCHAR, squad_id INTEGER, overall_rating INTEGER );
CREATE TABLE IF NOT EXISTS squads (squad_id int PRIMARY KEY auto_increment,name VARCHAR,  objective VARCHAR, homeground VARCHAR);




/*

INSERT INTO squads (name, objective,homeground) VALUES ('Juventus', 'Win the UEFA Europa League','Juventus Stadium');
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Lionel Messi',32, 'Dribbling','None',2,94);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Cristiano Ronaldo',33, 'Finishing','None',10,93);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Virgil van Dijk',28, 'Tackling','None',6,90);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Nicolas Pepe',23, 'Dribbling','Finishing',1,85);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Anthony Martial',24, 'Dribbling','Consistency',7,84);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Bernd Leno',27, 'Reflexes','Long Shots',1,84);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('David De Gea',28, 'Reflexes','Handling',7,89);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Paul Pogba',28, 'Passing','Defending',7,87);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Kylian Mbappe',21, 'Finishing','Passing',0,88);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Sadio Mane',28, 'Finishing','Passing',6,88);
INSERT INTO players (name,age,special_power,weakness,squad_id,overall_rating) VALUES ('Pierre-Emerick Aubameyang',29, 'Finishing','Penalties',1,88);


INSERT INTO squads (name, objective,homeground) VALUES ('Arsenal', 'Qualify for UEFA Champions League','The Emirates Stadium');
INSERT INTO squads (name, objective,homeground) VALUES ('Barcelona', 'Deliver the UEFA Champions League','Camp Nou');
INSERT INTO squads (name, objective,homeground) VALUES ('Real Madrid', 'Deliver the UEFA Champions League','Estadio Santiago Bernabeu');
INSERT INTO squads (name, objective,homeground) VALUES ('Tottenham Hotspurs', 'Apply Pressure','Tottenham Stadium');
INSERT INTO squads (name, objective,homeground) VALUES ('Chelsea', 'Qualify for UEFA Champions League','Stamford Bridge');
INSERT INTO squads (name, objective,homeground) VALUES ('Liverpool', 'Defend the UEFA Champions League','Anfield');
INSERT INTO squads (name, objective,homeground) VALUES ('Manchester United', 'Qualify for UEFA Champions League','Old Trafford');
INSERT INTO squads (name, objective,homeground) VALUES ('Everton', 'Qualify for UEFA Europa League','Goodison Park');
INSERT INTO squads (name, objective,homeground) VALUES ('Wolverhampton Wanderers', 'Qualify for UEFA Europa League','Mollineux Stadium');


*/

