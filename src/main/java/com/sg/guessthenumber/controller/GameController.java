package com.sg.guessthenumber.controller;

import com.sg.guessthenumber.dto.Game;
import com.sg.guessthenumber.dto.Round;
import com.sg.guessthenumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/game")
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
    public ResponseEntity<Round> makeGuess(@RequestBody Round round) {
        Round updatedRound = gameService.guessNumber(round);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRound);
    }
    @GetMapping("/")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable int gameId) {
        Game game = gameService.getGameById(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
    @GetMapping("/rounds/{gameId}")
    public ResponseEntity<List<Round>> getRoundsByGameId(@PathVariable int gameId) {
        List<Round> rounds = gameService.getRoundsByGameId(gameId);
        return ResponseEntity.status(HttpStatus.OK).body(rounds);
    }
}