package com.komaf.server.domain.board;

import com.komaf.server.domain.exception.WrongPositionsException;

import java.util.List;

public class DirectionChecker {

    public static Direction getDirectionOfNewShip(List<Integer> positions, int boardDimension) {
        if (positions.size() == 1) {
            return Direction.SINGLE;
        }
        int sumOfDifferences = 0;
        int newShipSize = positions.size();
        for (int i = 0; i < newShipSize - 1; i++) {
            sumOfDifferences += positions.get(i+1) - positions.get(i);
        }

        if (sumOfDifferences == newShipSize-1) {
            return Direction.VERTICAL;
        } else if (sumOfDifferences == (newShipSize-1) * boardDimension) {
            return Direction.HORIZONTAL;
        } else {
            throw new WrongPositionsException();
        }
    }
}
