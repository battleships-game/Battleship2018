package com.komaf.client.controllers;

import com.komaf.client.model.player.Player;
import com.komaf.client.model.room.Room;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomRestApiController {

    Room room = new Room();

    @GetMapping("/get")
    @ResponseBody
    public Room getRoom() {
        return new Room(new Player("jan"));
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<Room> getRooms() {

        RestTemplate restTemplate = new RestTemplate();
        List<Room> response = restTemplate.getForObject("http://localhost:8085/rooms/getAll",List.class);

        return response;
    }

    @PutMapping("/add")
    @ResponseBody
    public List<Room> addRoom(@RequestParam(name="player", required=false, defaultValue="Stranger") String player) {

        throw new NotImplementedException();
//                rooms.addRoom(player);
//        return rooms.getRoom2s();
    }

}
