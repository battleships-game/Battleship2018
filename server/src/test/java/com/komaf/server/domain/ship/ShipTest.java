package com.komaf.server.domain.ship;

import com.komaf.server.domain.exception.WrongShipTypeException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ShipTest {
    @Test
    public void testCreateThreeMastShipBasedOnGivenPositions() throws WrongShipTypeException {
        List<Integer> positions = Arrays.asList(11,12,13);
        Ship ship = ShipFactory.createShipFromPositions(positions);
        ShipType result = ship.getShipType();
        assertEquals(result, ShipType.III_MAST);
    }
    @Test
    public void testCreateOneMastShipBasedOnGivenPosition() throws WrongShipTypeException {
        List<Integer> positions = Arrays.asList(2);
        Ship ship = ShipFactory.createShipFromPositions(positions);
        ShipType result = ship.getShipType();
        assertEquals(result, ShipType.I_MAST);
    }
    @Test
    public void testCreateTwoMastShipBasedOnGivenPosition() throws WrongShipTypeException {
        List<Integer> positions = Arrays.asList(6,7);
        Ship ship = ShipFactory.createShipFromPositions(positions);
        ShipType result = ship.getShipType();
        assertEquals(result, ShipType.II_MAST);
    }
    @Test
    public void testCreateFourMastShipBasedOnGivenPosition() throws WrongShipTypeException {
        List<Integer> positions = Arrays.asList(14,24,34,44);
        Ship ship = ShipFactory.createShipFromPositions(positions);
        ShipType result = ship.getShipType();
        assertEquals(result, ShipType.IV_MAST);
    }
}
