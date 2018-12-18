package com.komaf.server.service;

import com.komaf.server.domain.room.Room;
import com.komaf.server.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public List<Room> getFreeRooms() {
        return roomRepository.getFreeRooms();
    }

    public boolean save(Room room) {
        return roomRepository.save(room);
    }
}
