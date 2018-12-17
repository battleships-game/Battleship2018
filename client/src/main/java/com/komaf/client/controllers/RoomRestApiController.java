package com.komaf.client.controllers;

import com.komaf.client.model.room.Room;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomRestApiController {

    @Value("${service.url}")
    private String url;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Room> getRooms() {

        RestTemplate restTemplate = new RestTemplate();
        List<Room> response = restTemplate.getForObject(url + "/room/getAll", List.class);

        return response;
    }

    @PostMapping("/add")
    @ResponseBody
    public String addRoom(@RequestParam(name="name") String playerName) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("name", playerName);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        System.out.printf(url);
        ResponseEntity<String> response = restTemplate.postForEntity(url + "/room/add", request, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return "OK";
        else
            return "Bad";
    }

}
