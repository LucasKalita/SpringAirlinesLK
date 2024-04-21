package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.exceptions.WrongMessageIDException;
import com.lucaskalita.airlines.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public List<MessageDTO> findAllMessages() {
        log.trace("Searching for all Messages");
        return messageRepository.findAll()
                .stream()
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDTO> findAllSentMessages(User user) {
        log.trace("Searching for all Messages sent to me by {}", user);
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getReceiver().equals(user))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDTO> findAllMessagesSentByUser(User user) {
        log.trace("Searching for all Messages sent by me");
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getSender().equals(user))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<MessageDTO> findConversationBetween(User user1, User user2) {
        log.trace("Searching for all Messages sent between {} and {}", user1, user2);
        return messageRepository.findAll()
                .stream()
                .filter(x -> x.getSender().equals(user1))
                .filter(x -> x.getReceiver().equals(user2))
                .map(messageMapper::fromEntityToDto)
                .collect(Collectors.toList());
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