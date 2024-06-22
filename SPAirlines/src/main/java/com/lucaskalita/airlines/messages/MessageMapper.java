package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.users.UserMapper;
import com.lucaskalita.airlines.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class MessageMapper implements Mapper<Message, MessageDTO> {
    private final UserMapper userMapper;
    @Override
    public MessageDTO fromEntityToDto(Message entity) {
        return MessageDTO.builder()
                .senderDto(userMapper.fromEntityToDto(entity.getSender()))
                .receiverDto(userMapper.fromEntityToDto(entity.getReceiver()))
                .postTime(entity.getPostDate())
                .content(entity.getContent())
                .build();
    }

    @Override
    public Message fromDtoToEntity(MessageDTO dto) {
        return Message.builder()
                .sender(userMapper.fromDtoToEntity(dto.senderDto()))
                .receiver(userMapper.fromDtoToEntity(dto.receiverDto()))
                .postDate(dto.postTime())
                .content(dto.content())
                .build();
    }
}
