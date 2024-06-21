package com.lucaskalita.airlines.shop;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class SeatDetailsDTO {
    String name;
    String surname;
    BigDecimal money;
    Boolean isPremium;

}
