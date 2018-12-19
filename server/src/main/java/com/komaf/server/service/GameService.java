package com.komaf.server.service;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.GameRepository;
import com.komaf.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private GameRepository gameRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public List<Game> getFreeGames() {
        return gameRepository.getFreeGames();
    }

    public boolean addNewGameByPlayer(Integer playerId) {
        Player player = playerRepository.findByID(playerId);
        return gameRepository.save(new Game(player));
    }

    public Game findById(Integer gameId) {
        return gameRepository.findAll()
                .stream()
                .filter(x->x.getId().equals(gameId))
                .findFirst()
                .get();
    }

    public Game findByPlayerId(Integer playerId) {
        return gameRepository.findAll()
                .stream()
                .filter(x->(x.getPlayer1().getId().equals(playerId)||x.getPlayer2().getId().equals(playerId)))
                .findFirst()
                .get();
    }

    public boolean joinGameByPlayer(Integer playerId, Integer gameId) {
        Player player = playerRepository.findByID(playerId);
        Game game = gameRepository.findById(gameId);
        return game.addPlayer(player);
    }

    public GameStatus getGameStatusByGameId(Integer gameId) {
        return gameRepository.findById(gameId).getGameStatus();
    }
}
