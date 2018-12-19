package com.komaf.client.utils;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestUtils {

    public ResponseEntity<String> sendRequest(Map<String, String> attributes, String url, HttpMethod httpMethod)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.setAll(attributes);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//        System.out.printf(url);
        ResponseEntity<String> response = null;
        if(httpMethod.equals(HttpMethod.POST))
            response = restTemplate.postForEntity(url, request, String.class);
        else if(httpMethod.equals(HttpMethod.GET))
//            response = restTemplate.getForObject(url, ResponseEntity.class, map);
            response = restTemplate.getForEntity(url, String.class);

        return response;
    }

}
