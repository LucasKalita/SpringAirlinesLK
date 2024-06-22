package com.lucaskalita.airlines.messages;
import com.lucaskalita.airlines.users.User;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private String content;
    private LocalDateTime postDate;


}
