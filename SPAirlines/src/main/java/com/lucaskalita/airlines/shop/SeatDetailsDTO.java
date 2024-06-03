package com.lucaskalita.airlines.shop;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class SeatDetailsDTO {
    String name;
    String surname;
    BigDecimal money;
    boolean isPremium;

}
