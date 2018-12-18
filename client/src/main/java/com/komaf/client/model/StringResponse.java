package com.komaf.client.model;

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