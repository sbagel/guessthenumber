package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;

import java.util.List;

    public interface GameDao {
        int createGame(Game game);
        Game getGameById(int id);
        List<Game> getAllGames();
        void updateGame(Game game);
        void deleteGame(int gameId);
    }


