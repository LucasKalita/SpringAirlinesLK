package com.lucaskalita.airlines.globalExceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class WrongObjectIdException extends RuntimeException{
    public WrongObjectIdException(String message){
        super(message);
    }
}
