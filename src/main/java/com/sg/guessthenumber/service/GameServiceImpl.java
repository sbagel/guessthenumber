package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final GameDao gameDao;
    private final RoundDao roundDao;

    @Autowired
    public GameServiceImpl (GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    @Override
    public String generateAnswer() {
        Random random = new Random();
        String number = "";
        HashSet<Integer> set = new HashSet<>();
        while(true) {
            int randomNum = random.nextInt(10);
            if (!set.contains(randomNum)) {
                set.add(randomNum);
                number += String.valueOf(randomNum);
            }
            if (set.size() == 4) break;
        }
        return number;
    }

    @Override
    public int beginGame() {
        String answer = generateAnswer();
        Game game = new Game(answer, false);
        return gameDao.createGame(game);
    }

    @Override
    public Round guessNumber(Round round) {
        Game game = gameDao.getGameById(round.getGameId());
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
        roundDao.createRound(round);

        if (e == 4) {
            game.setFinished(true);
            gameDao.updateGame(game);
        }
        return round;
    }

    @Override
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = gameDao.getGameById(gameId);
        if (game == null) {
            throw new RuntimeException("Game ID does not exist.");
        }
        return game;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        List<Round> rounds = roundDao.getRoundsByGameId(gameId);
        if (rounds == null) {
            throw new RuntimeException("Game ID does not exist.");
        }
        return rounds;
    }
}
