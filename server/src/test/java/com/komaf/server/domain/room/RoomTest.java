package com.komaf.server.domain.room;

import com.komaf.server.domain.game.Game;
import com.komaf.server.domain.game.GameStatus;
import com.komaf.server.domain.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RoomTest {
    @Test
    public void shouldReturnFalseIfThirdPlayerWantsJoin(){
        Game room = new Game(new Player("Mikołaj"), new Player("Michał"));
        boolean result = room.addPlayer(new Player("Filip"));
        assertFalse(result);
    }
    @Test
    public void shouldChangeRoomStatusToOccupiedWhenSecondPlayerJoinsToGame(){
        Game room = new Game(new Player("Mikołaj"));
        room.addPlayer(new Player("Michał"));
        GameStatus roomStatus = room.getGameStatus();
        assertEquals(GameStatus.READY, roomStatus);
    }
}
