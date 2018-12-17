package com.komaf.server.domain.board;

import com.komaf.server.domain.player.Player;
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
        return true;
    }

    private ShipType identifyShip(List<Integer> newShipPositions){
        return null;
    }
}
