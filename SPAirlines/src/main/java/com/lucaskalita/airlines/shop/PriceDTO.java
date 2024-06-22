package com.lucaskalita.airlines.shop;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record PriceDTO(
        BigDecimal price
) {
}
