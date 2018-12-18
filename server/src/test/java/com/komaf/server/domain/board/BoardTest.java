package com.komaf.server.domain.board;

import com.komaf.server.domain.player.Player;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class BoardTest {

    @Test
    public void testAddShipToBoard() {

        Board board = BoardFactory.createEmptyBoard(new Player("Janusz"));
        List<Integer> positions = Arrays.asList(2,3,4);
        board.addShipToBoard(positions);
        assertEquals(board.getAvailableShips().size(), 3);
    }
}