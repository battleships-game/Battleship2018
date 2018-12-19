package com.komaf.server.domain.exception;

public class FieldsOccupiedException extends RuntimeException {
    public String getMessage() {
        return "Podane pola są już zajęte!!! Proszę wybrać inne pola dla nowego statku!!!";
    }
}
