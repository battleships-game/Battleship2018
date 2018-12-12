package com.komaf.server.domain.room;

import com.komaf.server.domain.player.Player;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room{

    private static final AtomicInteger count = new AtomicInteger(0);
    Integer _id;
    Player player1;
    Player player2;
    RoomStatus roomStatus;


    public Room(Player player1) {
        this._id = count.incrementAndGet();
        this.player1 = player1;
        this.roomStatus = RoomStatus.FREE;
    }

    public Room(Player player1, Player player2) {
        this._id = count.incrementAndGet();
        this.player1 = player1;
        this.player2 = player2;
        this.roomStatus = RoomStatus.OCCUPIED;
    }
}
