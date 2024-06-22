package com.lucaskalita.airlines.globalExceptions;


public class WrongObjectIdException extends RuntimeException{
    public WrongObjectIdException(String message){
        super(message);
    }
}
