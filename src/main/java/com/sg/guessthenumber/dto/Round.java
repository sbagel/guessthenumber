package com.sg.guessthenumber.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class Round {
    private int roundId;
    private int gameId;
    private String guess;
    private LocalDateTime guessTime;
    private String result;

    // Constructors, getters, and setters

    // Constructor without roundId (used when creating a new round)
    public Round(int gameId, String guess, LocalDateTime guessTime, String result) {
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = guessTime;
        this.result = result;
    }

    // Constructor with roundId (used when retrieving round from database)
    public Round(int roundId, int gameId, String guess, LocalDateTime guessTime, String result) {
        this.roundId = roundId;
        this.gameId = gameId;
        this.guess = guess;
        this.guessTime = guessTime;
        this.result = result;
    }

    public Round() {

    }

    // Getters and setters
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public LocalDateTime getGuessTime() {
        return guessTime;
    }

    public void setGuessTime(LocalDateTime guessTime) {
        this.guessTime = guessTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return getRoundId() == round.getRoundId() && getGameId() == round.getGameId() && Objects.equals(getGuess(), round.getGuess()) && Objects.equals(getGuessTime(), round.getGuessTime()) && Objects.equals(getResult(), round.getResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoundId(), getGameId(), getGuess(), getGuessTime(), getResult());
    }
}


