package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageDTO findMessageByID(Long id) {
        log.trace("Searching for message by id: {}", id);
        return messageRepository.findById(id).map(messageMapper::fromEntityToDto)
                .orElseThrow(() -> new WrongObjectIdException("No message with this id: " + id));
    }

    public MessageDTO createMessage(MessageDTO messageDTO) {
        log.trace("Creating a new message");
        Message message = messageMapper.fromDtoToEntity(messageDTO);
        Message savedMessage = messageRepository.save(message);
        return messageMapper.fromEntityToDto(savedMessage);
    }

    public void deleteMessageById(Long id) {
        log.trace("Deleting message by id: " + id);
        messageRepository.deleteById(id);
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
                .map(messageMapper::fromEntityToDto).toList();
    }

    public List<MessageDTO> findAllMessagesSentBefore(LocalDateTime localDateTime) {
        log.trace("Searching for all Messages sent before {}", localDateTime);
        return messageRepository.findAllByDateTimeBefore(localDateTime)
                .stream()
                .map(messageMapper::fromEntityToDto)
                .toList();
    }

    public List<MessageDTO> findAllMessagesSentAfter(LocalDateTime localDateTime) {
        log.trace("Searching for all Messages sent after {}", localDateTime);
        return messageRepository.findAllByDateTimeAfter(localDateTime)
                .stream()
                .map(messageMapper::fromEntityToDto)
                .toList();
    }

    public List<MessageDTO> findAllMessagesSentBetween(LocalDateTime localDateTime, LocalDateTime localDateTime2) {
        log.trace("Searching for all Messages sent before {}", localDateTime);
        return messageRepository.findAllByDateTimeBetween(localDateTime, localDateTime2)
                .stream().map(messageMapper::fromEntityToDto)
                .toList();
    }
}