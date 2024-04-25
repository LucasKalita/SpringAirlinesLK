package com.lucaskalita.airlines.exceptions;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String s) {
        super(s);
    }
}
