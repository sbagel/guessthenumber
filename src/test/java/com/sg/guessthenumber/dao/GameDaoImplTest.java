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
            gameDao.deleteGame(game.getId());
        }

        List<Round> rounds = roundDao.getAllRounds();
        for (Round round : rounds){
            roundDao.deleteRound(round.getId());
        }
    }

    @Test
    public void testAddAndGetNewGame() {
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        Game addedGame = gameDao.addGame(game);

        Game fetchedGame = gameDao.getGameById(addedGame.getId());

        assertEquals(addedGame, fetchedGame);
    }

    @Test
    public void getAllGames(){
        Game finishedGame = new Game();
        finishedGame.setAnswer("1234");
        finishedGame.setFinished(true);
        Game addedFinished = gameDao.addGame(finishedGame);

        Game newGame = new Game();
        newGame.setAnswer("6132");
        newGame.setFinished(false);
        Game addedNew = gameDao.addGame(newGame);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(finishedGame));
        assertTrue(games.contains(addedNew));
    }

    @Test
    public void testUpdateGame(){
        Game game = new Game();
        game.setAnswer("6132");
        game.setFinished(false);
        Game addedGame = gameDao.addGame(game);

        Game fetchedGame = gameDao.getGameById(addedGame.getId());

        // before updating, fetchedGame's finish status should be false
        assertFalse(fetchedGame.isFinished());

        // gameDao.updateGame will update its finished status to true
        gameDao.updateGame(fetchedGame.getId());

        fetchedGame = gameDao.getGameById(addedGame.getId());

        // will check that the finish status is now true
        assertTrue(fetchedGame.isFinished());
    }
}