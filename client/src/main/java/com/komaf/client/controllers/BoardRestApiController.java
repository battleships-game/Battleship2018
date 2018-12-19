package com.komaf.client.controllers;

import com.komaf.client.utils.CookieData;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardRestApiController {

    @Value("${service.url}")
    private String url;

    @GetMapping("/validate")
    public String fieldsValidate(@ModelAttribute(value = "jObject") List<String> myArray) {

        OkHttpClient client = new OkHttpClient();
        if (CookieData.getPlayerId() == null) return "Zarejestruj najpierw gracza";
        RequestBody formBody = new FormBody.Builder()
                .add("playerId", CookieData.getPlayerId().toString())
                .add("positions", myArray.toString().replace("[", "").replace("]", ""))
                .build();

        Request request = new Request.Builder()
                .url(url + "/board/placeShip")
                .put(formBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) return "OK";
            else return "Bad";
        } catch (IOException e) {
            e.printStackTrace();
            return "Nie działa";
        }

    }

    @GetMapping("/save")
    public String fieldsSave(@ModelAttribute(value = "jObject") List<Integer> myArray) {
        if (myArray.size() == 20) {
            return "OK";
        } else {
            return "Bad";
        }
    }

}
