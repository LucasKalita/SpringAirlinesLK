package com.lucaskalita.airlines.globalExceptions;

import com.lucaskalita.airlines.utilities.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(WrongObjectIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO userException(WrongObjectIdException wrongObjectIdException){
        return ExceptionDTO.builder()
                .message("User not found")
                .build();
    }
}
