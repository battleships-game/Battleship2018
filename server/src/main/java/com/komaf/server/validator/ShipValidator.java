package com.komaf.server.validator;

import com.komaf.server.domain.board.*;
import com.komaf.server.domain.exception.FieldsOccupiedException;
import com.komaf.server.domain.exception.ShipNotAddedException;
import com.komaf.server.domain.exception.WrongPositionsException;
import com.komaf.server.domain.ship.ShipFactory;
import com.komaf.server.domain.ship.ShipType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShipValidator {
    public void checkIfShipInBoard(List<Integer> positions, Board board) {
        boolean isShipInBoard = positions.stream().allMatch(position -> position > 0 && position <= board.getBoardSize());
        if (!isShipInBoard) {
            throw new WrongPositionsException();
        }
    }

    public ShipType validateShipType(List<Integer> positions) {
        return ShipFactory.createShipFromPositions(positions).getShipType();
    }

    public void validateIfNewShipInTheSameRow(List<Integer> positions, int boardDimension) {
        Direction direction = DirectionChecker.getDirectionOfNewShip(positions, boardDimension);
        if (direction == Direction.VERTICAL) {
            int first = positions.get(0) - 1;
            int last = positions.get(positions.size() - 1) - 1;
            boolean areInTheSameRow = first / boardDimension == last / boardDimension;
            if (!areInTheSameRow) {
                throw new WrongPositionsException();
            }
        }
    }

    public void checkIfShipCanBeAddToBoard(ShipType shipType, Board board) {
        List<ShipType> availableShips = board.getAvailableShips();
        if (!checkIfShipTypeAvailableToAdd(shipType, availableShips)) {
            throw new ShipNotAddedException();
        }
    }

    public void checkIfPositionForNewShipAreWater(List<Integer> positions, Board board) {
       boolean freeToAdd = positions.stream().allMatch(position -> board.getFields().get(position - 1).getStatus() == Status.WATER);
       if (!freeToAdd) {
           throw new FieldsOccupiedException();
       }
    }

    private boolean checkIfShipTypeAvailableToAdd(ShipType shipType, List<ShipType> availableShips) {
        return availableShips.stream().anyMatch(shipType::equals);
    }
}
