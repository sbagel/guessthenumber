package com.sg.guessthenumber.service;

import com.sg.guessthenumber.dao.GameDao;
import com.sg.guessthenumber.dao.RoundDao;
import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final GameDao gameDao;
    private final RoundDao roundDao;

    @Autowired
    public GameServiceImpl (GameDao gameDao, RoundDao roundDao) {
        this.gameDao = gameDao;
        this.roundDao = roundDao;
    }
    @Override
    public String generateAnswer() {
        Random random = new Random();
        String number = "";
        HashSet<Integer> set = new HashSet<>();

        while (true) {
            // Generate a random number between 0 and 9
            int randomNum = random.nextInt(10);
            // Check if the number is not already in the set
            if (!set.contains(randomNum)) {
                // Add the number to the set
                set.add(randomNum);
                // Append the number to the string
                number += String.valueOf(randomNum);
            }
            // Check if we have generated four unique numbers
            if (set.size() == 4)
                break;
        }

        return number;
    }

    @Override
    public int beginGame() {
        // Generate a random answer using the generateAnswer() method
        String answer = generateAnswer();
        // Create a new Game object with the generated answer and set it as unfinished
        Game game = new Game(answer, false);
        // Save the game object to the data access object and return the generated game ID
        return gameDao.createGame(game);
    }


    @Override
    public Round guessNumber(Round round) {
        // Get the game object based on the game ID in the round
        Game game = gameDao.getGameById(round.getGameId());
        String answer = game.getAnswer();
        String guess = round.getGuess();
        // Check if the game is already finished
        if (game.isFinished()) {
            throw new RuntimeException("Cannot place guess for a finished game.");
        }

        int e = 0;
        int p = 0;
        // Iterate over each character position in the strings (assuming the strings are always of length 4)
        for (int i = 0; i < 4; i++) {
            // Get the character at the i-th position in the answer string
            char answerChar = answer.charAt(i);
            // Get the character at the i-th position in the guess string
            char guessChar = guess.charAt(i);
            // Check if the guess character is present in the answer string
            if (answer.contains(String.valueOf(guessChar))) {
                // Check if the characters at the same position match exactly
                if (answerChar == guessChar) {
                    e++;
                } else {
                    p++;
                }
            }
        }
        // Set the result of the round as a formatted string combining the exact and partial match counts
        round.setResult("e:" + e + ":p:" + p);
        // Save the round object to the database
        roundDao.createRound(round);
        // Check if all characters are exact matches
        if (e == 4) {
            // Set the game as finished
            game.setFinished(true);
            // Update the game object in the database
            gameDao.updateGame(game);
        }

        return round;
    }


    @Override
    //Retrieve all games from the data access object and return the list
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    @Override
    public Game getGameById(int gameId) {
        // Retrieve the game by its ID from the data access object
        Game game = gameDao.getGameById(gameId);
        if (game == null) {  // Check if the game object is null
            throw new RuntimeException("Game ID does not exist.");
        }
        return game;
    }

    @Override
    public List<Round> getRoundsByGameId(int gameId) {
        // Retrieve the rounds associated with the given game ID from the data access object
        List<Round> rounds = roundDao.getRoundsByGameId(gameId);
        if (rounds == null) {
            throw new RuntimeException("Game ID does not exist.");
        }
        return rounds;
    }

}
