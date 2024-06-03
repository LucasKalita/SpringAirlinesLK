package com.lucaskalita.airlines.globalExceptions;

public class NoEmptySeatsException extends RuntimeException {
    public NoEmptySeatsException (String message){super(message);}
}
