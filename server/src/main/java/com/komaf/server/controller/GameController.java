package com.komaf.server.controller;


import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import com.komaf.server.service.PlayerService;
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
    private final PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
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
        if (playerId.equals(Integer.valueOf(-1)))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Player player = playerService.findByID(playerId);
        Game game = new Game(player);

        if(gameService.save(game))
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/join")
    ResponseEntity<Game> joinGame(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId,
                                  @RequestParam(value = "gameId", defaultValue = "-1") Integer gameId) {
        if (playerId.equals(Integer.valueOf(-1))||gameId.equals(Integer.valueOf(-1)))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Player player = playerService.findByID(playerId);

        Game game = gameService.findByID(gameId);

        if(game.addPlayer(player))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/check")
    ResponseEntity<GameStatus> checkGameStatus(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId,
                                               @RequestParam(value = "gameId", defaultValue = "-1") Integer gameId) {
        if (playerId.equals(Integer.valueOf(-1))||gameId.equals(Integer.valueOf(-1)))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Player player = playerService.findByID(playerId);

        Game game = gameService.findByID(gameId);

        HttpStatus status = HttpStatus.ACCEPTED;
        if(game.addPlayer(player)) status = HttpStatus.CONFLICT;

        return ResponseEntity.status(status).body(game.getGameStatus());
    }

    @PostMapping("/findGameByPlayer")
    ResponseEntity<Game> checkGame(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {
        if (playerId.equals(Integer.valueOf(-1)))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Player player = playerService.findByID(playerId);

        Game game = gameService.findByPLayerID(playerId);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
    }


}
