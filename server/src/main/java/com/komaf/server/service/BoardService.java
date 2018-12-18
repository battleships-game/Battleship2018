package com.komaf.server.service;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.board.BoardFactory;
import com.komaf.server.domain.player.Player;
import com.komaf.server.repository.BoardRepository;
import com.komaf.server.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private PlayerRepository playerRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, PlayerRepository playerRepository) {
        this.boardRepository = boardRepository;
        this.playerRepository = playerRepository;
    }

    //TODO: co jeśli dostaniemy id dla którego nie znajdziemy playera - wyjątek?
    //TODO: dodać przekazanie wiadomości o problemach przy tworzeniu boarda lub playera - wyjątek?
    public boolean initializeBoard(Integer playerId) {
        Player player = playerRepository.findByID(playerId);
        Board board = BoardFactory.createEmptyBoard(player);
        return boardRepository.save(board);
    }

    public void shoot() {
        //TODO: dokończyć metodę shoot
    }

    //TODO: dodać obsługę błędów na podstawie wyjątku/booleana
    public void setShipOnBoard(Integer playerId, List<Integer> positions) {
        Player player = playerRepository.findByID(playerId);
        Board board = boardRepository.findByID(player.getBoardId());
        board.addShipToBoard(positions);
    }



}
