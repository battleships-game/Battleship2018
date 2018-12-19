package com.komaf.server.service;

import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public boolean save(Player player) {
        return playerRepository.save(player);
    }

    public Player findByID(int playerId) {
        return playerRepository.findByID(playerId);
    }


}
