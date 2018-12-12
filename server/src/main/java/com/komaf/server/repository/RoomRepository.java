package com.komaf.server.repository;

import com.komaf.server.domain.room.Room;
import com.komaf.server.domain.room.RoomStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RoomRepository{

    private List<Room> roomList;

    public RoomRepository(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<Room> findAll() {
        return roomList;
    }

    public ResponseEntity<Room> save(Room newRoom) {
        roomList.add(newRoom);
        return new ResponseEntity<>(HttpStatus.CREATED);
        }

    public List<Room> getFreeRooms() {
        return roomList.stream().filter(a -> a.getRoomStatus().equals(RoomStatus.FREE)).collect(Collectors.toList());
    }
}
