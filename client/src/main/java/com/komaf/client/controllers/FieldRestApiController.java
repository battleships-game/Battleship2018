package com.komaf.client.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fields")
public class FieldRestApiController {

    @GetMapping("/validate")
    public String fieldsValidate(@ModelAttribute(value="jObject") List<List<String>> myArray){
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
