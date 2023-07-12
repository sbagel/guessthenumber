 DROP DATABASE IF EXISTS guessthenumberdb;

 create database guessthenumberdb;
 use guessthenumberdb;

-- Table structure for game

DROP TABLE IF EXISTS game;
CREATE TABLE game (
  game_id INT PRIMARY KEY AUTO_INCREMENT,
  answer VARCHAR(4) NOT NULL,
  finished BOOLEAN DEFAULT false
);

-- Table structure for round

DROP TABLE IF EXISTS round;
CREATE TABLE round (
  round_id INT PRIMARY KEY AUTO_INCREMENT,
  game_id INT NOT NULL,
  guess VARCHAR(4) NOT NULL,
  guess_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  result VARCHAR(7) NOT NULL,
  FOREIGN KEY (game_id) REFERENCES Game(game_id)
);