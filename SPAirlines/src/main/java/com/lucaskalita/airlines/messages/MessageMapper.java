package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper implements Mapper<Message, MessageDTO> {
    @Override
    public MessageDTO fromEntityToDto(Message entity) {
        return MessageDTO.builder()
                .id(entity.getId())
                .sender(entity.getSender())
                .receiver(entity.getReceiver())
                .dateTime(entity.getDateTime())
                .content(entity.getContent())
                .build();
    }

    @Override
    public Message fromDtoToEntity(MessageDTO dto) {
        return Message.builder()
                .id(dto.id())
                .sender(dto.sender())
                .receiver(dto.receiver())
                .dateTime(dto.dateTime())
                .content(dto.content())
                .build();
    }
}
