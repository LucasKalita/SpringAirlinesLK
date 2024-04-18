package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.users.User;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record MessageDTO(
        Long id,
        User sender,
        User receiver,
        String content,
        LocalDateTime dateTime
) {
}
