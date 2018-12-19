package com.komaf.server.domain.board;

import com.komaf.server.domain.exception.WrongPositionsException;
import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipFactory;
import com.komaf.server.domain.ship.ShipType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private int boardSize;
    private List<Field> fields;
    private List<ShipType> availableShips;

    Board(List<Field> fields, Player player, List<ShipType> availableShips) {
        this.id = count.incrementAndGet();
        this.fields = fields;
        player.setBoardId(this.id);
        this.availableShips = availableShips;
        this.boardSize = fields.size();
    }

    public void addShipToBoard(List<Integer> newShipPositions, Status fieldStatus, ShipType shipType) {

            newShipPositions.forEach(position -> fields.get(position).setStatus(fieldStatus));
            availableShips.remove(shipType);
    }

    public int getBoardDimension() {
        return (int) Math.sqrt(boardSize);
    }
    private boolean checkIfShipTypeAvailableToAdd(ShipType shipType) {
        return availableShips.stream().anyMatch(shipType::equals);
    }

}
