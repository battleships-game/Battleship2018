package com.komaf.client.controllers;

import com.komaf.client.utils.CookieData;
import com.komaf.client.utils.RestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    @Value("${service.url}")
    private String url;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexController(Map<String, Object> model) {
        if(CookieData.getPlayerId()!=null)
        {
            Map<String,String> attributes = new HashMap<>();
            RestUtils restUtils = new RestUtils();
            ResponseEntity<String> response = restUtils.sendRequest(attributes, url + "/player/getAll", HttpMethod.GET);
            model.put("name", "");
        }
        return "index";
    }

    @RequestMapping(value = "/waitingRoom", method = RequestMethod.GET)
    public String setRoomController() {
        return "room";
    }

    @RequestMapping(value = "/setBoard", params = { "r" }, method = RequestMethod.GET)
    public String setBoard(@RequestParam("r") long roomId) {

        return "settingBoard";
    }

    @RequestMapping(value = "/playGame/{roomId}", method = RequestMethod.GET)
    public String setGameController(@PathVariable("roomId") int roomId) {
        return "board";
    }


}
