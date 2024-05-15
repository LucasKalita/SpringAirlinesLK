package com.lucaskalita.airlines.globalExceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;


public class WrongObjectIdException extends RuntimeException{
    public WrongObjectIdException(String message){
        super(message);
    }
}
