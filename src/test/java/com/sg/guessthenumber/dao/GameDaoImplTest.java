package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameDaoImplTest {
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
    public void testAddAndGetNewGame() {
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        int gameId = gameDao.createGame(game);
        game.setGameId(gameId);

        Game fetchedGame = gameDao.getGameById(gameId);

        assertEquals(game, fetchedGame);
    }

    @Test
    public void getAllGames(){
        Game finishedGame = new Game();
        finishedGame.setAnswer("1234");
        finishedGame.setFinished(true);
        int addedFinished = gameDao.createGame(finishedGame);

        Game newGame = new Game();
        newGame.setAnswer("6132");
        newGame.setFinished(false);
        int addedNew = gameDao.createGame(newGame);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(gameDao.getGameById(addedFinished)));
        assertTrue(games.contains(gameDao.getGameById(addedNew)));
    }

    @Test
    public void testUpdateGame(){
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        int gameId = gameDao.createGame(game);
        game.setGameId(gameId);

        Game fetchedGame = gameDao.getGameById(gameId);

        // before updating, fetchedGame's finish status should be false
        assertFalse(fetchedGame.isFinished());

        // gameDao.updateGame will update its finished status to true
        game.setFinished(true);
        gameDao.updateGame(game);

        fetchedGame = gameDao.getGameById(gameId);

        // will check that the finish status is now true
        assertTrue(fetchedGame.isFinished());
    }
}