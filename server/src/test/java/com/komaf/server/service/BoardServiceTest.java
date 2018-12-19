package com.komaf.server.service;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.BoardRepository;
import com.komaf.server.repository.PlayerRepository;
import com.komaf.server.validator.ShipValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class BoardServiceTest {
    BoardRepository boardRepository;
    PlayerRepository playerRepository;
    ShipValidator shipValidator;
    Player player;
    @Before
    public void initialize(){
         List<Board> boardList = new ArrayList<>();
         boardRepository = new BoardRepository(boardList);
         playerRepository = new PlayerRepository();
         shipValidator = new ShipValidator();
         player = new Player("Mikołaj");
         playerRepository.save(player);
    }
    @Test
    public void shouldInitializeBoard(){
        BoardService boardService = new BoardService(boardRepository,playerRepository, shipValidator);
        boolean result = boardService.initializeBoard(player.getId());
        assertTrue(result);
    }
}
