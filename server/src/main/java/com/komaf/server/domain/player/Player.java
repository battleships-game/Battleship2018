package com.komaf.server.domain.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    private static final AtomicInteger count = new AtomicInteger(0);

    private Integer id;
    private Integer boardId;
    private String name;

    public Player(String name) {
        this.id = count.incrementAndGet();
        this.name = name;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }
}
