package com.komaf.server.service;

import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.GameRepository;
import com.komaf.server.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameServiceTest {
    GameRepository gameRepository;
    PlayerRepository playerRepository;
    GameService gameService;

    @Before
    public void initialize(){
        gameRepository = new GameRepository();
        playerRepository = new PlayerRepository();
        gameService = new GameService(gameRepository, playerRepository);
    }
    @Test
    public void shouldReturnListWithGames(){
        gameRepository.save(new Game());
        gameRepository.save(new Game());
        List<Game> listOfGames = gameService.findAll();
        assertEquals(2, listOfGames.size());
    }
    @Test
    public void shouldReturnTrueWhenWeAddNewGame(){
        GameService gameService = new GameService(gameRepository,playerRepository);
        Player player = new Player("Mikołaj");
        playerRepository.save(player);
        boolean result = gameService.addNewGameByPlayer(player.getId());
        assertTrue(result);
    }
    @Test
    public void shouldAllowPlayerToJoinToTheGame(){
        Player player = new Player("Mikołaj");
        Player player2 = new Player("Michał");
        playerRepository.save(player);
        playerRepository.save(player2);
        Game game = new Game(player);
        gameRepository.save(game);
        GameService gameService = new GameService(gameRepository,playerRepository);
        boolean result =  gameService.joinGameByPlayer(player2.getId(), game.getId());
        assertTrue(result);
    }
    @Test
    public void shouldReturnGameStateIsWaitForPlay(){
        Game game = new Game(new Player("Mikołaj"));
        gameRepository.save(game);
        GameService gameService = new GameService(gameRepository, playerRepository);
        GameStatus status = gameService.getGameStatusByGameId(game.getId());
        assertEquals(GameStatus.WAIT_FOR_PLAYER,status);
    }
    @Test
    public void shouldReturnGameById(){
        GameService gameService = new GameService(gameRepository, playerRepository);
        Game game = new Game(new Player("Mikołaj"));
        gameRepository.save(game);
        Game game1 = gameService.findById(game.getId());
        assertEquals(game,game1);
    }

    @Test
    public void shouldReturnGameByPlayerId(){
        GameService gameService = new GameService(gameRepository, playerRepository);
        Player player = new Player("Mikołaj");
        Game game = new Game(player);
        gameRepository.save(game);
        Game game1 = gameService.findByPlayerId(player.getId());
        assertEquals(game, game1);
    }

    @Test
    public void shouldReturnGameByPlayerId2(){
        GameService gameService = new GameService(gameRepository, playerRepository);
        Player player = new Player("Mikołaj");
        Player player2 = new Player("Mikołaj");
        Game game = new Game(player,player2);
        gameRepository.save(game);
        Game game1 = gameService.findByPlayerId(player.getId());
        assertEquals(game, game1);
    }

    @Test
    public void shouldReturnGameByPlayerId3(){
        GameService gameService = new GameService(gameRepository, playerRepository);
        Player player = new Player("Mikołaj");
        Player player2 = new Player("Mikołaj2");
        Game game = new Game(player, player2);
        Player player3 = new Player("Mikołaj3");
        Player player4 = new Player("Mikołaj4");
        Game game2 = new Game(player3, player4);
        gameRepository.save(game);
        gameRepository.save(game2);
        Game game1 = gameService.findByPlayerId(player3.getId());
        assertEquals(game2, game1);
    }

}
