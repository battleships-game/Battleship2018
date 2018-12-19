package com.komaf.server.repository;

import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.player.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class GameRepositoryTest {
    @Test
    public void checkIfEmptyGameRepositoryReturnEmptyListOfFreeGames() {
        GameRepository gameRepository = new GameRepository();
        int expected = 0;
        List<Game> games = gameRepository.getFreeGames();

        assertEquals(games.size(), expected);
    }
    @Test
    public void checkIfSaveMethodAddNewGamesToEmptyGameRepository() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"));
        int expected = 2;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.findAll();
        assertEquals(games.size(), expected);
    }
    @Test
    public void checkIfGameRepositoryWithTwoGamesReturnListOfFreeGamesWithTwoGames() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"));
        int expected = 2;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        assertEquals(games.size(), expected);
    }
    @Test
    public void checkIfGameRepositoryWithOneFreeAndOneOccupiedGameReturnListOfFreeGamesWithOneGame() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"));
        Game gameTwo = new Game(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 1;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        assertEquals(games.size(), expected);
    }
    @Test
    public void checkIfGameRepositoryWithTwoOccupiedGamesReturnEmptyListOfFreeGames() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"), new Player("Kunegunda"));
        Game gameTwo = new Game(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 0;

        gameRepository.save(game);
        gameRepository.save(gameTwo);

        List<Game> games = gameRepository.getFreeGames();
        assertEquals(games.size(), expected);
    }
    @Test
    public void shouldReturnGameWithChooseId(){
        GameRepository gameRepository = new GameRepository();
        Game game = new Game(new Player("Janusz"), new Player("Kunegunda"));
        gameRepository.save(game);
        Game getGameById = gameRepository.findById(game.getId());
        assertEquals(getGameById, game);
    }
}