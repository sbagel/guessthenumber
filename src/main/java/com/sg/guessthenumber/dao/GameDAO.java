package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;

import java.util.List;

public interface GameDAO {
    int createGame(Game game);

    Game getGameById(int gameId);

    List<Game> getAllGames();

    void updateGame(Game game);
}
