package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.exceptions.WrongMessageIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {

   private final MessageRepository messageRepository;
   private final MessageMapper messageMapper;

    public MessageDTO findMessageByID(Long id) {
        log.trace("Searching for message by id: {}", id);
        return messageRepository.findById(id)
                .map(messageMapper::fromEntityToDto)
                .orElseThrow(() -> new WrongMessageIDException("No message with this id: " + id));
    }
    public MessageDTO createMessage(MessageDTO messageDTO) {
        log.trace("Creating a new message");
        Message message = messageMapper.fromDtoToEntity(messageDTO);
        Message savedMessage = messageRepository.save(message);
        return messageMapper.fromEntityToDto(savedMessage);
    }

    public List<MessageDTO> findAllSentMessagesToUser(String username) {
        log.trace("Searching for all Messages sent to me by {}", username);
        return messageRepository.findAllMessagesByReceiverUsername(username)
                .stream()
                .map(messageMapper::fromEntityToDto)
                .toList();
    }

    public List<MessageDTO> findAllMessagesSentByUser(String username) {
        log.trace("Searching for all Messages sent by me");
        return messageRepository.findAllMessagesBySenderUsername(username)
                .stream()
                .map(messageMapper::fromEntityToDto)
                .toList();
    }
    public List<MessageDTO> findConversationBetween(String username1, String username2) {
        log.trace("Searching for all Messages sent between {} and {}", username2, username1);
        return messageRepository.findConversationBetween(username1, username2)
                .stream()
                .map(messageMapper::fromEntityToDto)
                .toList();
    }

    public List<MessageDTO> findAllMessagesSentBefore(LocalDateTime localDateTime) {
        log.trace("Searching for all Messages sent before {}", localDateTime);
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getDateTime().isBefore(localDateTime))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDTO> findAllMessagesSentAfter(LocalDateTime localDateTime) {
        log.trace("Searching for all Messages sent after {}", localDateTime);
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getDateTime().isAfter(localDateTime))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<MessageDTO> findAllMessagesSentBetween(LocalDateTime localDateTime, LocalDateTime localDateTime2){
        log.trace("Searching for all Messages sent before {}", localDateTime);
        return messageRepository.findAll()
                .stream()
                .filter(x->x.getDateTime().isAfter(localDateTime))
                .filter(x->x.getDateTime().isBefore(localDateTime2))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
}
}