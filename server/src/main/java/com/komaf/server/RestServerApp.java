package com.komaf.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class RestServerApp {

    public static void main(String[] args) {
            SpringApplication.run(RestServerApp.class, args);
        }

}
