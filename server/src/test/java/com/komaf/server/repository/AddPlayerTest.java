package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AddPlayerTest {
    @Test
    public void shouldAddPlayerToList(){
        PlayerRepository playerRepository = new PlayerRepository();
        boolean result =playerRepository.save(new Player("Pierwszy"));
        assertTrue(result);
    }




}
