package com.komaf.server.domain.board;

import com.komaf.server.domain.exception.WrongPositionsException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DirectionCheckerTest {

    @Test
    public void testGetDirectionFor4MastShipVertical() {
        List<Integer> positions = Arrays.asList(2,3,4,5);
        Direction result = DirectionChecker.getDirectionOfNewShip(positions, 10);
        assertEquals(Direction.VERTICAL, result);
    }

    @Test
    public void testGetDirectionFor3MastShipHorizontal() {
        List<Integer> positions = Arrays.asList(24,34,44);
        Direction result = DirectionChecker.getDirectionOfNewShip(positions, 10);
        assertEquals(Direction.HORIZONTAL, result);
    }

    @Test(expected = WrongPositionsException.class)
    public void testThrowExceptionIfPositionsAreBad() {
        List<Integer> positions = Arrays.asList(12,34,46);
        Direction result = DirectionChecker.getDirectionOfNewShip(positions, 10);
    }

    @Test
    public void testGetDirectionForSinglePosition() {
        List<Integer> positions = Arrays.asList(55);
        Direction result = DirectionChecker.getDirectionOfNewShip(positions, 10);
        assertEquals(result, Direction.SINGLE);
    }

}