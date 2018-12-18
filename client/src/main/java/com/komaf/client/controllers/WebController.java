package com.komaf.client.controllers;

import com.komaf.client.utils.CookieData;
import com.komaf.client.utils.RestUtils;
import org.json.JSONException;
import org.json.JSONObject;
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
        model.put("name", "");
        if(CookieData.getPlayerId()!=null)
        {
            Map<String,String> attributes = new HashMap<>();
            RestUtils restUtils = new RestUtils();
            ResponseEntity response = restUtils.sendRequest(attributes, url + "/player/get/"+CookieData.getPlayerId().toString(), HttpMethod.GET);
            try {
                JSONObject obj = new JSONObject(response.getBody().toString());
                model.put("name", obj.getString("name"));

            } catch (JSONException e) {
                model.put("name", "");
            }
        }
        return "index";
    }

    @RequestMapping(value = "/waitingRoom", method = RequestMethod.GET)
    public String setRoomController() {
        return "waitingRoom";
    }

    @RequestMapping(value = "/setBoard", params = { "r" }, method = RequestMethod.GET)
    public String setBoard(@RequestParam("r") long roomId) {

        return "settingBoard";
    }

    @RequestMapping(value = "/playGame", params = { "r" }, method = RequestMethod.GET)
    public String setGameController(@RequestParam("r") long roomId) {
        return "board";
    }


}
