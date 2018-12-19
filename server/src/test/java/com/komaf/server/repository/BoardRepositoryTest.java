package com.komaf.server.repository;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.board.BoardFactory;
import com.komaf.server.domain.player.Player;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BoardRepositoryTest {
    @Test
    public void shouldAddBoardToList(){
        List<Board> boardList = new ArrayList<>();
        BoardRepository boardRepository = new BoardRepository(boardList);
        Board board = BoardFactory.createEmptyBoard(new Player("Mikołaj"));
        boolean result =boardRepository.save(board);
        assertTrue(result);
    }
    @Test
    public void shouldReturnBoardWithChooseId(){
        List<Board> boardList = new ArrayList<>();
        BoardRepository boardRepository = new BoardRepository(boardList);
        Board board = BoardFactory.createEmptyBoard(new Player("Mikołaj"));
        boardRepository.save(board);
        Board getBoardById = boardRepository.findByID(board.getId());
        assertEquals(getBoardById, board);
    }
}
