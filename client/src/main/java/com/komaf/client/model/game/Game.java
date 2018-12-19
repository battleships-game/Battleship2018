package com.komaf.client.model.game;

import com.komaf.client.model.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    private static final AtomicInteger count = new AtomicInteger(0);
    Integer _id;
    Player player1;
    Player player2;
    GameStatus roomStatus;


    public Game(Player player1) {
        this._id = count.incrementAndGet();
        this.player1 = player1;
        this.roomStatus = GameStatus.FREE;
    }

    public Game(Player player1, Player player2) {
        this._id = count.incrementAndGet();
        this.player1 = player1;
        this.player2 = player2;
        this.roomStatus = GameStatus.OCCUPIED;
    }
}
