package com.komaf.server.exception_handler;

import com.komaf.server.domain.exception.GameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GameExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected String gameNotFoundHandler(GameNotFoundException ex) {
        return ex.getMessage();
    }
}
