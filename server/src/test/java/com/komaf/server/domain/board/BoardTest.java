package com.komaf.server.domain.board;

import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class BoardTest {

    @Test
    public void testAdd3MastShipToBoardWithVaildPositions() {
        Board board = BoardFactory.createEmptyBoard(new Player("Janusz"));
        List<Integer> positions = Arrays.asList(2,3,4);
        board.addShipToBoard(positions, Status.III_MAST, ShipType.III_MAST);
        assertEquals(3, board.getAvailableShips().size());
    }
}