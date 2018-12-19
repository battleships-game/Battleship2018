package com.komaf.server.domain.exception;

public class ShipNotAddedException extends RuntimeException {
    public String getMessage() {
        return "Nie można dodać statku o podanej ilości masztów!!! Spróbuj dodać statek o innej liczbie masztów";
    }
}
