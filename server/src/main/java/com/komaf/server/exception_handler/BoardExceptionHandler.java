package com.komaf.server.exception_handler;

import com.komaf.server.domain.exception.WrongPositionsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BoardExceptionHandler {

    @ResponseBody
    @ExceptionHandler({
            WrongPositionsException.class,

    })
    @ResponseStatus(HttpStatus.CONFLICT)
    protected String wrongPositionExcHandler(WrongPositionsException ex) {
        return ex.getMessage();
    }

}
