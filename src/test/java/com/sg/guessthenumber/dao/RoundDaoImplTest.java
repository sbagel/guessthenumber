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
            gameDao.deleteGame(game.getGameId());
        }

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds){
            roundDao.deleteRound(round.getRoundId());
        }
    }

    @Test
    public void testAddAndGetNewRound() {
        // create game
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        int addedGame = gameDao.createGame(game);

        // add round
        Round round = new Round();
        round.setGuessTime(LocalDateTime.now());
        round.setGameId(addedGame);
        round.setGuess("1234");
        round.setResult("e:1:p:2");
        int addedRound = roundDao.createRound(round);

        List<Round> fetchedRounds = roundDao.getRoundsByGameId(addedGame);

        assertEquals(1,fetchedRounds.size());
    }
}