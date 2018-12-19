package com.komaf.server.service;

import com.komaf.server.domain.board.*;
import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.ship.ShipType;
import com.komaf.server.repository.BoardRepository;
import com.komaf.server.repository.PlayerRepository;
import com.komaf.server.validator.ShipValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private PlayerRepository playerRepository;
    private ShipValidator shipValidator;

    @Autowired
    public BoardService(BoardRepository boardRepository, PlayerRepository playerRepository, ShipValidator shipValidator) {
        this.boardRepository = boardRepository;
        this.playerRepository = playerRepository;
        this.shipValidator = shipValidator;
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

    public ResponseEntity setShipOnBoard(Integer playerId, List<Integer> positions) {
        Player player = playerRepository.findByID(playerId);
        Board board = boardRepository.findByID(player.getBoardId());
        int boardDimension = board.getBoardDimension();
        shipValidator.checkIfShipInBoard(positions, board);

        ShipType shipType = shipValidator.validateShipType(positions);
        shipValidator.validateIfNewShipInTheSameRow(positions, boardDimension);
        shipValidator.checkIfShipCanBeAddToBoard(shipType, board);
        shipValidator.checkIfPositionForNewShipAreWater(positions, board);
        Status status = identifyFieldStatus(shipType);
        board.addShipToBoard(positions, status, shipType);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }




    private Status identifyFieldStatus(ShipType shipType) {
        switch (shipType) {
            case I_MAST:
                return Status.I_MAST;
            case II_MAST:
                return Status.II_MAST;
            case III_MAST:
                return Status.III_MAST;
            case IV_MAST:
                return Status.IV_MAST;
            default:
                return Status.WATER;
        }
    }

    public Board getBoardById(Integer id) {
        return boardRepository.findByID(id);
    }
}

