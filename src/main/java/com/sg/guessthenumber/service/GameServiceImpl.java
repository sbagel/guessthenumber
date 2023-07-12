package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.model.Game;
import com.sg.guessthenumber.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements ServiceLayer {

    private GameDao gameDao;
    private RoundDao roundDao;

    @Autowired
    public GameServiceImpl (GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    @Override
    public String generateAnswer() {
        Random random = new Random();
        String number = "";
        for (int i = 0; i < 4; i++) {
            number += String.valueOf(random.nextInt(10));
        }
        return number;
    }

    @Override
    public int beginGame() {
        String answer = generateAnswer();
        Game game = new Game(answer, false);
        game = gameDao.add(game);
        return game.getId();
    }

    @Override
    public Round guessNumber(Round round) {
        Game game = gameDao.findById(round.getGameId());
        String answer = game.getAnswer();
        String guess = round.getGuess();

        if (game.isFinished()) {
            throw new RuntimeException("Cannot place guess for a finished game.");
        }

        int e = 0;
        int p = 0;

        for (int i = 0; i < 4; i++) {
            char answerChar = answer.charAt(i);
            char guessChar = guess.charAt(i);

            if (answer.contains(String.valueOf(guessChar))) {
                if (answerChar == guessChar) {
                    e++;
                } else {
                    p++;
                }
            }
        }
        round.setResult("e:" + e + ":p:" + p);
        roundDao.add(round);

        if (e == 4) {
            game.setFinished(true);
            gameDao.update(game);
        }
        return round;
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAll();
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = gameDao.findById(gameId);
        if (game == null) {
            throw new RuntimeException("Game ID does not exist.");
        }
        return game;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        List<Round> rounds = roundDao.findByInt(gameId);
        if (rounds == null) {
            throw new RuntimeException("Game ID does not exist.");
        }
        return rounds;
    }
}
