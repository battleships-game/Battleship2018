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
@RequestMapping("/rooms")
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

    @GetMapping("/test")
    void fillTestData(){
        roomRepository.save(new Room(new Player("jan")));
        roomRepository.save(new Room(new Player("janusz")));
        roomRepository.save(new Room(new Player("magda")));
        roomRepository.save(new Room(new Player("piotr")));
        roomRepository.save(new Room(new Player("pioter"), new Player("Andrzej")));
    }

    @PostMapping("/")
    ResponseEntity<Room> addRoom(@RequestBody Room newRoom) {
        return roomRepository.save(newRoom);
    }

}
