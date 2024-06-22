package com.lucaskalita.airlines.messages;

import lombok.Builder;

@Builder
public record NoteDTO(
        String message
) {
}
