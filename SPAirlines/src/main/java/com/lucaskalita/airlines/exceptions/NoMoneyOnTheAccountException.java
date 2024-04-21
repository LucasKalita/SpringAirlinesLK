package com.lucaskalita.airlines.exceptions;

public class NoMoneyOnTheAccountException extends RuntimeException {
    public NoMoneyOnTheAccountException(String s) {
        super(s);
    }
}
