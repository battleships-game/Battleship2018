package com.komaf.server.service;

import com.komaf.server.domain.game.Game;
import com.komaf.server.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public List<Game> getFreeGames() {
        return gameRepository.getFreeGames();
    }

    public boolean save(Game game) {
        return gameRepository.save(game);
    }

    public Game findByID(Integer gameId) {
        return gameRepository.findAll().stream().filter(x->x.getId().equals(gameId)).findFirst().get();
    }

    public Game findByPLayerID(Integer playerId) {
        return gameRepository.findAll().stream().filter(x->x.getPlayer1().getId().equals(playerId)).findFirst().get();
    }
}
