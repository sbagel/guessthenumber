package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import com.sg.guessthenumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @PostMapping("/begin")
    public ResponseEntity<Integer> beginGame() {
        int gameId = gameService.beginGame();
        return ResponseEntity.status(HttpStatus.CREATED).body(gameId);
    }
    @PostMapping("/guess")
    public ResponseEntity<?> makeGuess(@RequestBody Round round) {
        try {
            round.setGuessTime(LocalDateTime.now());
            Round guessNumber = gameService.guessNumber(round);
            return ResponseEntity.ok(guessNumber);
        } catch (RuntimeException ex){
            Error error = new Error();
            error.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }
    @GetMapping("/game/{gameId}")
    public ResponseEntity<?> getGameById(@PathVariable int gameId) {
        try{
            Game game = gameService.getGameById(gameId);
            return ResponseEntity.ok(game);
        } catch (RuntimeException ex){
            Error error = new Error();
            error.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<?> getRoundsByGameId(@PathVariable int gameId) {
        try {
            List<Round> rounds = gameService.getRoundsByGameId(gameId);
            return ResponseEntity.ok(rounds);
        } catch (RuntimeException ex){
            Error error = new Error();
            error.setMessage(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}