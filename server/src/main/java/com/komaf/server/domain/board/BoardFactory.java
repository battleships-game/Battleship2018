package com.komaf.server.domain.board;

import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFactory {

    public static Board createEmptyBoard(Player player) {
        List<Field> emptyFields = new ArrayList<>();
        IntStream.range(1, 101).forEach(i-> emptyFields.add(new Field(i, Status.WATER)));
        return new Board(emptyFields, player, prepareAvailableShips());
    }

    private static List<ShipType> prepareAvailableShips(){
        List<ShipType> availableShips = new ArrayList<>();
        availableShips.add(ShipType.I_MAST);
        availableShips.add(ShipType.II_MAST);
        availableShips.add(ShipType.III_MAST);
        availableShips.add(ShipType.IV_MAST);
        return availableShips;
    }
}
