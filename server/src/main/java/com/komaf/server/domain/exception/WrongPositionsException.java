package com.komaf.server.domain.exception;

public class WrongPositionsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Podana pozycja dla nowego statku jest niedopuszczalna!!! Proszę wybrać pozycję jeszcze raz!!!";
    }
}
