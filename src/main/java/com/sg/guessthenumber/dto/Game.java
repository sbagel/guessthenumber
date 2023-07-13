package com.sg.guessthenumber.dto;

import java.util.Objects;

public class Game {
    private int gameId;
    private String answer;
    private boolean finished;

    // Constructors, getters, and setters

    // Constructor without gameId (used when creating a new game)
    public Game(String answer, boolean finished) {
        this.answer = answer;
        this.finished = finished;
    }

    // Constructor with gameId (used when retrieving game from database)
    public Game(int gameId, String answer, boolean finished) {
        this.gameId = gameId;
        this.answer = answer;
        this.finished = finished;
    }

    public Game() {

    }

    // Getters and setters
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGameId() == game.getGameId() && isFinished() == game.isFinished() && Objects.equals(getAnswer(), game.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getAnswer(), isFinished());
    }
}
