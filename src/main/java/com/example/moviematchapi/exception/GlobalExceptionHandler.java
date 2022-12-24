package com.example.moviematchapi.exception;

import com.example.moviematchapi.exception.customException.NoSuchSessionException;
import com.example.moviematchapi.exception.customException.NoSuchUserInSessionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NoSuchSessionException.class, NoSuchUserInSessionException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorDetails handleNotFound (RuntimeException ex){
        return new ErrorDetails(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
