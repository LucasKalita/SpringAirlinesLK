package com.lucaskalita.airlines.utilities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Builder
public record ExceptionDTO(String message) {
}
