package com.komaf.client.controllers;

import com.komaf.client.model.StringResponse;
import com.komaf.client.utils.CookieData;
import com.komaf.client.utils.RestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/player")
public class PlayerRestApiController {

    @Value("${service.url}")
    private String url;

    @GetMapping("/getAll")
    @ResponseBody
    public String getPlayers() {

        Map<String,String> attributes = new HashMap<>();
        RestUtils restUtils = new RestUtils();
        ResponseEntity<String> response = restUtils.sendRequest(attributes, url + "/player/getAll", HttpMethod.GET);
        return response.toString();
    }

    @PostMapping("/add")
    @ResponseBody
    public StringResponse addPlayer(@RequestParam(name="name") String playerName) {

        Map<String,String> attributes = new HashMap<>();
        attributes.put("name", playerName);
        RestUtils restUtils = new RestUtils();
        ResponseEntity<String> response = restUtils.sendRequest(attributes, url + "/player/add", HttpMethod.POST);
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        CookieData.parseCookie(cookies.get(0));

        if(response.getStatusCode().is2xxSuccessful())
            return new StringResponse("OK");
        else
            return new StringResponse("Bad");
    }

    @GetMapping("/getGameId")
    @ResponseBody
    public String getPlayersGame() {

        Map<String,String> attributes = new HashMap<>();
        attributes.put("playerId", String.valueOf(CookieData.getPlayerId()));
        RestUtils restUtils = new RestUtils();
        ResponseEntity<String> response = restUtils.sendRequest(attributes, url + "/game/findGameByPlayer", HttpMethod.GET);
        return response.toString();
    }

}
