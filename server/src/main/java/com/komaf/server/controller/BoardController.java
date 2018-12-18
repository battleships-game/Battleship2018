package com.komaf.server.controller;

import com.komaf.server.domain.board.Board;
import com.komaf.server.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/init")
    ResponseEntity<Board> initializeBoard(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {
        HttpHeaders headers = new HttpHeaders();
        if(boardService.initializeBoard(playerId))
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).headers(headers).build();
    }


}
