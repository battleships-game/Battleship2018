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
public
class Player {

    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer _id;
    private Integer boardId;
    String name;

    public Player(String name) {
        this._id = count.incrementAndGet();
        this.name = name;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }
}
