package com.lucaskalita.airlines.globalExceptions;

import com.lucaskalita.airlines.globalExceptions.entity.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
    @ExceptionHandler({WrongObjectIdException.class, ObjectNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO ObjectException(WrongObjectIdException wrongObjectIdException){
        return ExceptionDTO.builder()
                .message("Object not found")
                .build();
    }

    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO fundException(InsufficientFundsException insufficientFundsException){
        return ExceptionDTO.builder()
                .message("Not enough funds on account")
                .build();
    }
}
