package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Game;

import java.util.List;

public interface GameDao {
    int createGame(Game game);
    void updateGame(Game game);
    Game getGameById(int gameId);
    List<Game> getAllGames();
}
