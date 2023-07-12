package com.sg.guessthenumber.dao;

import com.sg.guessthenumber.dto.Round;

import java.util.List;

public interface RoundDAO {
    int createRound(Round round);

    List<Round> getRoundsByGameId(int gameId);
}
