package com.lucaskalita.airlines.messages;

import lombok.Builder;

@Builder
public record ContentDTO(
        String message
) {
}
