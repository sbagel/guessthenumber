package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;

import java.util.List;

    public interface GameDao {
        Long createGame(Game game);
        Game getGameById(Long id);
        List<Game> getAllGames();
        void updateGame(Game game);
    }


