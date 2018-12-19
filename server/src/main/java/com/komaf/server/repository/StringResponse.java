package com.komaf.server.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StringResponse {

    private String response;

    public StringResponse(String s) {
        this.response = s;
    }



}