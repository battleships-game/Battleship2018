package com.komaf.server.domain.ship;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Ship {
    private List<Integer> newShipPositions;
    private ShipType shipType;

    public Ship(List<Integer> newShipPositions, ShipType shipType) {
        this.newShipPositions = newShipPositions;
        this.shipType = shipType;
    }
}


