package com.lucaskalita.airlines.globalExceptions;

public class SeatOccupiedException extends  RuntimeException{
    public SeatOccupiedException(){super("Seat occupied");}
}
