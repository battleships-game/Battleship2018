package com.komaf.server.domain.board;

import com.komaf.server.domain.exception.WrongShipTypeException;
import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipFactory;
import com.komaf.server.domain.ship.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class Board {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private List<Field> fields;
    private List<ShipType> availableShips;

    public Board(List<Field> fields, Player player, List<ShipType> availableShips) {
        this.id = count.incrementAndGet();
        this.fields = fields;
        player.setBoardId(this.id);
        this.availableShips = availableShips;
    }



    public boolean addShipToBoard(List<Integer> newShipPositions) {
        ShipType shipType;
        try {
            shipType = ShipFactory.createShipFromPositions(newShipPositions).getShipType();
        } catch (WrongShipTypeException e) {
            e.printStackTrace();
            return false;
        }
        if(checkIfShipTypeAvailableToAdd(shipType)) {
            Status fieldStatus = identifyFieldStatus(shipType);
            newShipPositions.forEach(position -> fields.add(position, new Field(position,fieldStatus)));
            availableShips.remove(shipType);
            return true;
        }
        return false;
    }

    private boolean checkIfShipTypeAvailableToAdd(ShipType shipType) {
        return availableShips.stream().anyMatch(type -> shipType.equals(type));
    }

    private Status identifyFieldStatus(ShipType shipType){
        switch (shipType) {
            case I_MAST:
                return Status.I_MAST;
            case II_MAST:
                return Status.II_MAST;
            case III_MAST:
                return Status.III_MAST;
            case IV_MAST:
                return Status.IV_MAST;
            default:
                return Status.WATER;
        }
    }
}
