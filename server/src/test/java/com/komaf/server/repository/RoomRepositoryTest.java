package com.komaf.server.repository;

import com.komaf.server.domain.player.Player;
import com.komaf.server.domain.room.Room;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class RoomRepositoryTest {

    public void checkIfEmptyRoomRepositoryReturnEmptyListOfFreeRooms() {
        RoomRepository roomRepository = new RoomRepository();
        int expected = 0;
        List<Room> rooms = roomRepository.getFreeRooms();

        Assert.assertEquals(rooms.size(), expected);
    }

    public void checkIfSaveMethodAddNewRoomsToEmptyRoomRepository() {
        RoomRepository roomRepository = new RoomRepository();
        Room room = new Room(new Player("Janusz"));
        Room roomTwo = new Room(new Player("Grażyna"));
        int expected = 2;

        roomRepository.save(room);
        roomRepository.save(roomTwo);

        List<Room> rooms = roomRepository.findAll();
        Assert.assertEquals(rooms.size(), expected);
    }

    public void checkIfRoomRepositoryWithTwoRoomsReturnListOfFreeRoomsWithTwoRooms() {
        RoomRepository roomRepository = new RoomRepository();
        Room room = new Room(new Player("Janusz"));
        Room roomTwo = new Room(new Player("Grażyna"));
        int expected = 2;

        roomRepository.save(room);
        roomRepository.save(roomTwo);

        List<Room> rooms = roomRepository.getFreeRooms();
        Assert.assertEquals(rooms.size(), expected);
    }

    public void checkIfRoomRepositoryWithOneFreeAndOneOccupiedRoomReturnListOfFreeRoomsWithOneRoom() {
        RoomRepository roomRepository = new RoomRepository();
        Room room = new Room(new Player("Janusz"));
        Room roomTwo = new Room(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 1;

        roomRepository.save(room);
        roomRepository.save(roomTwo);

        List<Room> rooms = roomRepository.getFreeRooms();
        Assert.assertEquals(rooms.size(), expected);
    }

    public void checkIfRoomRepositoryWithTwoOccupiedRoomsReturnEmptyListOfFreeRooms() {
        RoomRepository roomRepository = new RoomRepository();
        Room room = new Room(new Player("Janusz"), new Player("Kunegunda"));
        Room roomTwo = new Room(new Player("Grażyna"), new Player("Zbyszek"));
        int expected = 0;

        roomRepository.save(room);
        roomRepository.save(roomTwo);

        List<Room> rooms = roomRepository.getFreeRooms();
        Assert.assertEquals(rooms.size(), expected);
    }
}