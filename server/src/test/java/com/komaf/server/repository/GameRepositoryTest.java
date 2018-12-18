package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.game.Game;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class GameRepositoryTest {

    public void checkIfEmptyGameRepositoryReturnEmptyListOfFreeGames() {
        GameRepository gameRepository = new GameRepository();
        int expected = 0;
        List<Game> games = gameRepository.getFreeGames();

        Assert.assertEquals(games.size(), expected);
    }

    public void checkIfSaveMethodAddNewGamesToEmptyGameRepository() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"));
        int expected = 2;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.findAll();
        Assert.assertEquals(games.size(), expected);
    }

    public void checkIfGameRepositoryWithTwoGamesReturnListOfFreeGamesWithTwoGames() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"));
        int expected = 2;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        Assert.assertEquals(games.size(), expected);
    }

    public void checkIfGameRepositoryWithOneFreeAndOneOccupiedGameReturnListOfFreeGamesWithOneGame() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 1;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        Assert.assertEquals(games.size(), expected);
    }

    public void checkIfGameRepositoryWithTwoOccupiedGamesReturnEmptyListOfFreeGames() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"), new Player("Kunegunda"));
        Game gameTwo = new Game(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 0;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        Assert.assertEquals(games.size(), expected);
    }
}