package com.lucaskalita.airlines.utilities;

import lombok.Builder;
import lombok.Getter;


@Builder
public record ExceptionDTO(String message) {
}
