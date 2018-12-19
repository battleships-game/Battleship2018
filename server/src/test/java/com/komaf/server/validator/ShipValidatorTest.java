package com.komaf.server.validator;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.board.BoardFactory;
import com.komaf.server.domain.board.Status;
import com.komaf.server.domain.exception.FieldsOccupiedException;
import com.komaf.server.domain.exception.ShipNotAddedException;
import com.komaf.server.domain.exception.WrongPositionsException;
import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ShipValidatorTest {
    @Test(expected = WrongPositionsException.class)
    public void shouldThrowWrongPositionException(){
        List<Integer> position = Arrays.asList(-2,5,6);
        Board board = BoardFactory.createEmptyBoard(new Player("Mikołaj"));
        ShipValidator shipValidator = new ShipValidator();
        shipValidator.checkIfShipInBoard(position,board);
    }
    @Test(expected = FieldsOccupiedException.class)
    public void shouldThrowFieldsOccupiedException(){
        ShipValidator shipValidator = new ShipValidator();
        List<Integer> position = Arrays.asList(5,6);
        Board board = BoardFactory.createEmptyBoard(new Player("Mikołaj"));
        board.addShipToBoard(position, Status.II_MAST, ShipType.III_MAST);
        shipValidator.checkIfPositionForNewShipAreWater(position,board);
    }
    @Test(expected = WrongPositionsException.class)
    public void shouldThrowWrongPositionsExceptionWhenShipIsNotInTheSameRow(){
        ShipValidator shipValidator = new ShipValidator();
        List<Integer> positions = Arrays.asList(9,10,11);
        int boardDimension=10;
        shipValidator.validateIfNewShipInTheSameRow(positions,boardDimension);
        }

    @Test(expected = ShipNotAddedException.class)
    public void shouldThrowExceptionWhenWantAddShipNotAvailableToAdd() {
        Board board = BoardFactory.createEmptyBoard(new Player("Mikołaj"));
        ShipValidator shipValidator = new ShipValidator();
        List<Integer> positions = Arrays.asList(9,10);
        board.addShipToBoard(positions, Status.II_MAST, ShipType.II_MAST);
        shipValidator.checkIfShipCanBeAddToBoard(ShipType.II_MAST, board);
    }

}
