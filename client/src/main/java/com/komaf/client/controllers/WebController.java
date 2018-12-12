package com.komaf.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexController() {
        return "index";
    }

    @RequestMapping(value = "/setBoard", method = RequestMethod.GET)
    public String setBoardController() {
        return "settingBoard";
    }

    @RequestMapping(value = "/playGame", method = RequestMethod.GET)
    public String setGameController() {
        return "board";
    }



}
