package com.komaf.server.domain.board;

import com.komaf.server.domain.player.Player;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

@Test
public class BoardTest {

    public void testAddShipToBoard() {

        Board board = BoardFactory.createEmptyBoard(new Player("Janusz"));
        List<Integer> positions = Arrays.asList(2,3,4);
        board.addShipToBoard(positions);
        Assert.assertEquals(board.getAvailableShips().size(), 3);
    }
}