package com.lucaskalita.airlines.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository <Message, Long>{
    public List<Message> findAllMessagesBySenderUsername(String username);
    public List<Message> findAllMessagesByReceiverUsername(String username);
    @Query("SELECT m FROM Message m WHERE (m.sender.username = :username AND m.receiver.username = :username2) " +
            "OR (m.sender.username = :username2 AND m.receiver.username = :username1)")
    public List<Message> findConversationBetween(String username, String username2 );
    public List<Message> findAllByPostDateBefore(LocalDateTime postDate);
    public List<Message> findAllByPostDateAfter(LocalDateTime postDate);
    public List<Message> findAllByPostDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
