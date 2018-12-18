package com.komaf.server.domain.room;

import com.komaf.server.domain.player.Player;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {

    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private Player player1;
    private Player player2;
    private RoomStatus roomStatus;


    public Room(Player player1) {
        this.id = count.incrementAndGet();
        this.player1 = player1;
        this.roomStatus = RoomStatus.FREE;
    }

    public Room(Player player1, Player player2) {
        this.id = count.incrementAndGet();
        this.player1 = player1;
        this.player2 = player2;
        this.roomStatus = RoomStatus.OCCUPIED;
    }

    public boolean addPlayer(Player player2)
    {
        if(this.roomStatus.equals(RoomStatus.OCCUPIED)) return false;
        this.player2 = player2;
        this.roomStatus = RoomStatus.OCCUPIED;
        return true;
    }

}
