package com.komaf.server.domain.exception;

public class WrongShipTypeException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Podany typ statku jest nieprawidłowy!!! Proszę podać statek o odpowiedniej liczbie masztów (od 1 do 4)!!!";
    }
}
