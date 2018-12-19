package com.komaf.server.service;

import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerServiceTest {
    PlayerRepository playerRepository;
    @Before
    public void initialize(){
        playerRepository = new PlayerRepository();
    }
    @Test
    public void shouldAddPlayerToPlayerRepository(){
        PlayerService playerService = new PlayerService(playerRepository);
        boolean result = playerService.save(new Player("Mikołaj"));
        assertTrue(result);
    }
    @Test
    public void shouldReturnListWith3Players(){
        PlayerService playerService = new PlayerService(playerRepository);
        playerService.save(new Player("Mikołaj"));
        playerService.save(new Player("Michał"));
        playerService.save(new Player("Filip"));
        int result = playerService.findAll().size();
        assertEquals(3,result);
    }
    @Test
    public void shouldReturnPlayerById(){
        Player player = new Player("Mikołaj");
        playerRepository.save(player);
        PlayerService playerService = new PlayerService(playerRepository);
        Player player1 = playerService.findByID(player.getId());
        assertEquals(player, player1);
    }
}
