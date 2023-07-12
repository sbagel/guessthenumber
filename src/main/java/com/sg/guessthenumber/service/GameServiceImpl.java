package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameServiceImpl implements GameService {

    GameDao gameDao;

    RoundDao roundDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }

    @Override
    public String generateAnswer() {
        return null;
    }

    @Override
    public int beginGame() {
        return 0;
    }

    @Override
    public Round guessNumber(Round round) {
        return null;
    }

    @Override
    public List<Game> getAllGames() {
        return null;
    }

    @Override
    public Game getGameById(int gameId) {
        return null;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        return null;
    }
}
