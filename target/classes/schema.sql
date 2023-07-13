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

INSERT INTO game (game_id, answer, finished) VALUES
	(1, '5213', true),
	(2, '7168', false);

INSERT INTO round (round_id, game_id, guess_time, guess, result) VALUES
    (1, 1, '2023-07-01 07:25:18', '1234', 'e:1:p:2'),
    (2, 1, '2023-07-01 09:11:07', '3215', 'e:2:p:2'),
    (3, 1, '2023-07-01 09:11:49', '5213', 'e:4:p:0');