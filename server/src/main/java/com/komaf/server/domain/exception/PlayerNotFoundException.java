package com.komaf.server.domain.exception;

public class PlayerNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Użytkownik nie istnieje!";
    }
}
