package com.komaf.server.controller;

import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import com.komaf.server.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/getAll")
    List<Game> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/getAllFree")
    List<Game> getFreeGames() {
        return gameService.getFreeGames();
    }

    @PostMapping("/add")
    ResponseEntity<Game> addGame(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {

        int defaultValue = -1;
        if (playerId == defaultValue) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return gameService.addNewGameByPlayer(playerId) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/join")
    ResponseEntity<Game> joinGame(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId,
                                  @RequestParam(value = "gameId", defaultValue = "-1") Integer gameId) {
        int defaultValue = -1;
        if (playerId == defaultValue||gameId == defaultValue) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return gameService.joinGameByPlayer(playerId, gameId) ?
                new ResponseEntity<>(HttpStatus.ACCEPTED) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/check")
    ResponseEntity<GameStatus> checkGameStatus(@RequestParam(value = "gameId", defaultValue = "-1") Integer gameId) {
        int defaultValue = -1;
        if (gameId == defaultValue) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameService.getGameStatusByGameId(gameId));
    }

    @PostMapping("/findGameByPlayer")
    ResponseEntity<Game> checkGame(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {
        int defaultValue = -1;
        if (playerId == defaultValue) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameService.findByPlayerId(playerId));
    }


}
