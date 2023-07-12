package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;

import java.util.List;

public interface GameService {
    String generateAnswer();
    int beginGame();
    Round guessNumber(Round round);
    List<Game> getAllGames();
    Game getGameById(int gameId);
    List<Round> getRoundsByGameId(int gameId);
}
