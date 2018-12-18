package com.komaf.server.controller;


import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.room.Room;
import com.komaf.server.service.PlayerService;
import com.komaf.server.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private final PlayerService playerService;

    @Autowired
    private HttpSession session;

    @Autowired
    public RoomController(RoomService roomService, PlayerService playerService) {
        this.roomService = roomService;
        this.playerService = playerService;
    }

    @GetMapping("/getAll")
    List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @GetMapping("/getAllFree")
    List<Room> getFreeRooms() {
        return roomService.getFreeRooms();
    }

    @PostMapping("/add")
    ResponseEntity<Room> addRoom(@RequestParam(value = "playerId", defaultValue = "-1") Integer playerId) {
        if (playerId.equals(Integer.valueOf(-1)))
        {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Player player = playerService.findByID(playerId);
        Room room = new Room(player);

        if(roomService.save(room))
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
