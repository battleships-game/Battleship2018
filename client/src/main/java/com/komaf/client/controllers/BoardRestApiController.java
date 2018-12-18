package com.komaf.client.controllers;

import com.komaf.client.utils.CookieData;
import com.komaf.client.utils.RestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardRestApiController {

    @Value("${service.url}")
    private String url;

    @GetMapping("/validate")
    public String fieldsValidate(@ModelAttribute(value="jObject") List<String> myArray){

        Map<String,String> attributes = new HashMap<>();
        RestUtils restUtils = new RestUtils();
        attributes.put("playerId", "1");//CookieData.getPlayerId().toString());
        attributes.put("positions", myArray.toString());
        ResponseEntity<String> response = restUtils.sendRequest(attributes, url + "/board/placeShip", HttpMethod.POST);

        //return response.toString();

        if(myArray.size()==20) {
            return "OK";
        }
        else {
            return "Bad";
        }
    }

    @GetMapping("/save")
    public String fieldsSave(@ModelAttribute(value="jObject") List<Integer> myArray){
        if(myArray.size()==20) {
            return "OK";
        }
        else {
            return "Bad";
        }
    }

}
