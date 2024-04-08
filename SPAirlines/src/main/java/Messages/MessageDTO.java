package Messages;

import users.User;

import java.time.LocalDateTime;

public record MessageDTO(
        Long id,
        User sender,
        User receiver,
        String content,
        LocalDateTime dateTime
) {
}
