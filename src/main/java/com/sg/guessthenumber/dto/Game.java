package com.sg.guessthenumber.dto;

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
}
