package com.komaf.server.repository;

import com.komaf.server.domain.board.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepository{

    private List<Board> boardList;

    public BoardRepository(List<Board> boardList) {
        this.boardList = boardList;
    }

    public boolean save(Board newBoard) {
        boardList.add(newBoard);
        return true;
    }

    public Board findByID(int boardId){
        return boardList
                .stream()
                .filter(x->x.getId()==boardId)
                .findFirst()
                .get();
    }

}
