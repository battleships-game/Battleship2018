package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerRepositoryTest {
    @Test
    public void shouldAddPlayerToList(){
        PlayerRepository playerRepository = new PlayerRepository();
        boolean result =playerRepository.save(new Player("Pierwszy"));
        assertTrue(result);
    }
    @Test
    public void shouldReturnAllPlayersFromList(){
        PlayerRepository playerRepository = new PlayerRepository();
        playerRepository.save(new Player("Mikołaj"));
        playerRepository.save(new Player("Michał"));
        playerRepository.save(new Player("Filip"));
        int result =playerRepository.findAll().size();
        assertEquals(result, 3);
    }




}
