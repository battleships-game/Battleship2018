package com.komaf.server.domain.game;

import com.komaf.server.domain.player.Player;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private Player player1;
    private Player player2;
    private GameStatus gameStatus;


    public Game(Player player1) {
        this.id = count.incrementAndGet();
        this.player1 = player1;
        this.gameStatus = GameStatus.WAIT_FOR_PLAYER;
    }

    public Game(Player player1, Player player2) {
        this.id = count.incrementAndGet();
        this.player1 = player1;
        this.player2 = player2;
        this.gameStatus = GameStatus.READY;
    } //TODO: uzywane tylko w testach - do usunięcia!

    public boolean addPlayer(Player player2) {
        if(this.gameStatus.equals(GameStatus.READY)) return false;
        this.player2 = player2;
        this.gameStatus = GameStatus.READY;
        return true;
    }

}
