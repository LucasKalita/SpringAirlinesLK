package com.lucaskalita.airlines.exceptions;

public class WrongSeatIDException extends RuntimeException {
    public WrongSeatIDException (String s){
        super(s);
    }

}
