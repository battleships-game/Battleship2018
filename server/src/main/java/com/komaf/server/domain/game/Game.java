package com.komaf.server.domain.game;

import com.komaf.server.domain.player.Player;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private List<Player> playerList;
    private GameStatus gameStatus;


    public Game(Player player1) {
        playerList = new ArrayList<>();
        this.id = count.incrementAndGet();
        playerList.add(player1);
        this.gameStatus = GameStatus.WAIT_FOR_PLAYER;
    }

    public Game(Player player1, Player player2) {
        playerList = new ArrayList<>();
        this.id = count.incrementAndGet();
        playerList.add(player1);
        playerList.add(player2);
        this.gameStatus = GameStatus.READY;
    } //TODO: uzywane tylko w testach - do usunięcia!

    public boolean addPlayer(Player player2) {
        if(playerList.size()>=2) return false;
        if(this.gameStatus.equals(GameStatus.READY)) return false;
        playerList.add(player2);
        this.gameStatus = GameStatus.READY;
        return true;
    }

}
