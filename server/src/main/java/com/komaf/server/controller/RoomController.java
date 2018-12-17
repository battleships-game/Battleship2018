package com.komaf.server.controller;


import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.room.Room;
import com.komaf.server.domain.room.RoomStatus;
import com.komaf.server.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/getAll")
    List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/getAllFree")
    List<Room> getFreeRooms() {
        return roomRepository.getFreeRooms();
    }

    @PostMapping("/add")
    ResponseEntity<Room> addRoom(@RequestParam("name") String player) {
        return roomRepository.save(new Room(new Player(player)));
    }

}
