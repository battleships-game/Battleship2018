package com.komaf.server.domain.exception;

public class GameNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Gra nie istnieje!";
    }
}
