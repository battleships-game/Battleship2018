package com.komaf.client.controllers;

import com.komaf.client.model.StringResponse;
import com.komaf.client.model.game.Game;
import com.komaf.client.utils.CookieData;
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
@RequestMapping("/game")
public class GameRestApiController {

    @Value("${service.url}")
    private String url;

    @GetMapping("/getAll")
    @ResponseBody
    public List<Game> getGames() {

        RestTemplate restTemplate = new RestTemplate();
        List<Game> response = restTemplate.getForObject(url + "/game/getAll", List.class);

        return response;
    }

    @PostMapping("/add")
    @ResponseBody
    public StringResponse addGame(@RequestParam(name="name") String playerName) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.set("playerId", CookieData.getPlayerId().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "/game/add", request, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return new StringResponse("OK");
        else
            return new StringResponse("Bad");
    }

    @GetMapping("/wait")
    @ResponseBody
    public StringResponse waitForGame() {

        if(CookieData.getPlayerId()==null) return new StringResponse("Error");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.set("playerId", CookieData.getPlayerId().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "/game/checkGameByPlayer", request, String.class);

        return new StringResponse(response.getBody());
    }


/*
* @metoda do dołączenia do pokoju, jeszcze nie działa???
*
* */
    @GetMapping("/join")
    @ResponseBody
    public StringResponse joinGame() {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.set("playerId", CookieData.getPlayerId().toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "/game/wait", request, String.class);

        if(response.getStatusCode().is2xxSuccessful())
            return new StringResponse("OK");
        else
            return new StringResponse("Bad");

    }
}
