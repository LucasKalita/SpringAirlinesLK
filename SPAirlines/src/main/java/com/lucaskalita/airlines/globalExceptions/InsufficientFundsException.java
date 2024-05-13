package com.lucaskalita.airlines.globalExceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException (String message){
        super(message);
    }
}
