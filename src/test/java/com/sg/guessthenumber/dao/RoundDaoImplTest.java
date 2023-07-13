package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoundDaoImplTest {
    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    @BeforeEach
    public void setUp() {
        List<Game> games = gameDao.getAllGames();
        for(Game game : games) {
            gameDao.deleteGame(game.getId());
        }

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds){
            roundDao.deleteRound(round.getId());
        }
    }

    @Test
    public void testAddAndGetNewRound() {
        // create game
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        Game addedGame = gameDao.addGame(game);
        int gameId = addedGame.getId();

        // add round
        Round round = new Round();
        round.setGuessTime(LocalDateTime.now());
        round.setGame(gameId);
        round.setGuess("1234");
        round.setResult("e:1:p:2");
        Round addedRound = roundDao.addRound(round);

        List<Round> fetchedRounds = roundDao.getAllRoundsByGameId(gameId);

        assertEquals(1,fetchedRounds.size());
    }
}