package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.users.UserDTO;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record MessageDTO(

        UserDTO senderDto,
        UserDTO receiverDto,
        String content,
        LocalDateTime postDate

) {
}
