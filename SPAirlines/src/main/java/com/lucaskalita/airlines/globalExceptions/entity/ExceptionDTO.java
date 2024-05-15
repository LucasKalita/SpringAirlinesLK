package com.lucaskalita.airlines.globalExceptions.entity;

import lombok.Builder;
import lombok.Getter;


@Builder
public record ExceptionDTO(String message) {
}
