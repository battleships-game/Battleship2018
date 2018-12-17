package com.komaf.server.domain.ship;

import com.komaf.server.domain.exception.WrongShipTypeException;

import java.util.List;

public class ShipFactory {

    public Ship getShipFromPositions(List<Integer> positions) throws WrongShipTypeException {
        switch (positions.size()) {
            case 1:
                return new Ship(positions, ShipType.I_MAST);
            case 2:
                return new Ship(positions, ShipType.II_MAST);
            case 3:
                return new Ship(positions, ShipType.III_MAST);
            case 4:
                return new Ship(positions, ShipType.IV_MAST);
            default:
                throw new WrongShipTypeException("Exception: wrong type");
        }
    }
}
