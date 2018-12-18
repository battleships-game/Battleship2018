package com.komaf.server.repository;

import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameRepository {

    private List<Game> gameList;

    public GameRepository() {
        this.gameList = new ArrayList<>();
    }

    public List<Game> findAll() {
        return gameList;
    }

    public boolean save(Game newGame) {
        gameList.add(newGame);
        return true;
    }

    public List<Game> getFreeGames() {
        return gameList.stream().filter(game -> game.getGameStatus()
                .equals(GameStatus.WAIT_FOR_PLAYER))
                .collect(Collectors.toList());
    }
}
