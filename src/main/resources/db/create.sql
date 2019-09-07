SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS heroes ( id int PRIMARY KEY auto_increment, name VARCHAR, age NUMBER, special_power VARCHAR, weakness VARCHAR, squad_id NUMBER,overall_rating NUMBER);