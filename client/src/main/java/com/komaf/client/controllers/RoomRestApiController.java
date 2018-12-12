package com.komaf.client.controllers;

import com.komaf.client.model.player.Player;
import com.komaf.client.model.room.Room;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomRestApiController {

    @GetMapping("/getAll")
    @ResponseBody
    public List<Room> getRooms() {

        RestTemplate restTemplate = new RestTemplate();
        List<Room> response = restTemplate.getForObject("http://localhost:8085/room/getAll",List.class);

        return response;
    }

    @PostMapping("/add")
    @ResponseBody
    public List<Room> addRoom(@RequestParam(name="player", required=false, defaultValue="Stranger") String player) {

        RestTemplate restTemplate = new RestTemplate();
        HashMap<String,String> params = new HashMap<>();
        params.put("player",player);
        restTemplate.postForObject("http://localhost:8085/room/save", player, List.class, params);

        List<Room> response = restTemplate.getForObject("http://localhost:8085/room/getAll",List.class);
        return response;
    }

}
