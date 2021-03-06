package com.komaf.server.controller;

import com.komaf.server.domain.player.Player;
import com.komaf.server.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getAll")
    List<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @PostMapping("/add")
    ResponseEntity<Player> addPlayer(@CookieValue(value = "playerId", defaultValue = "-1") Long playerId,
                                     @RequestParam("name") String playerName, HttpServletResponse response) {
        Player player = new Player(playerName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "playerId=" + player.getId());

        if (playerService.save(player))
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).headers(headers).build();
    }

    @GetMapping(value="/get/{playerId}")
    ResponseEntity<Player> getPlayer(@PathVariable("playerId") Integer playerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.findByID(playerId));
    }
}
