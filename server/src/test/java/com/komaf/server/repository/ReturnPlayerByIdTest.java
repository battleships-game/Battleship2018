package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ReturnPlayerByIdTest {
    @Test
    public void shouldReturnPlayerNameFromID(){
        PlayerRepository playerRepository = new PlayerRepository();
        Player player1 = new Player("Marek");
        Player player2 = new Player("Michał");
        playerRepository.save(player1);
        playerRepository.save(player2);
        int playerID = player2.getId();
        Player playerByID = playerRepository.findByID(playerID);
        String result = playerByID.getName();
        assertTrue(player2.getName().equals(result));

    }
}
