package com.komaf.server.controller;

import com.komaf.server.domain.board.Board;
import com.komaf.server.domain.exception.WrongPositionsException;
import com.komaf.server.domain.exception.WrongShipTypeException;
import com.komaf.server.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/init")
    ResponseEntity initializeBoard(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {
        HttpHeaders headers = new HttpHeaders();
        if(boardService.initializeBoard(playerId)) return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).headers(headers).build();
    }

    @PutMapping("/placeShip")
    ResponseEntity putShipOnBoard(@RequestParam(value="playerId", defaultValue = "-1") Integer playerId, @RequestParam(value = "positions") List<Integer> positions){
        return boardService.setShipOnBoard(playerId, positions);
    }

    @GetMapping("/{id}")
    Board getBoardByPlayerId(@PathVariable(value="id") Integer id){
        return boardService.getBoardById(id);
    }


}
