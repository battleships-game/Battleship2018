package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {

    private List<Player> playerList;

    public PlayerRepository() {
        this.playerList = new ArrayList<>();
        playerList.add(new Player("Paweł"));
        playerList.add(new Player("Krzychu"));
    }

    public List<Player> findAll() {
        return playerList;
    }

    public boolean save(Player newPlayer) {
        playerList.add(newPlayer);
        return true;
    }

    public Player findByID(int playerId){
        return playerList.stream().filter(x->x.getId()==playerId).findFirst().get();
    }

}
