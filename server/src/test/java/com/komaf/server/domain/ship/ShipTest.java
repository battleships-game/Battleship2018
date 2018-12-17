package com.komaf.server.domain.ship;

import com.komaf.server.domain.exception.WrongShipTypeException;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test
public class ShipTest {

    public void testCreateThreeMastShipBasedOnGivenPositions() throws WrongShipTypeException {
        List<Integer> positions = Arrays.asList(11,12,13);
        Ship ship = ShipFactory.createShipFromPositions(positions);
        ShipType result = ship.getShipType();
        assertEquals(result, ShipType.III_MAST);
    }
}
