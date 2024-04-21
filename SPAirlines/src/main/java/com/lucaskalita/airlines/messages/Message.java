package com.lucaskalita.airlines.messages;

import com.lucaskalita.airlines.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    private String content;
    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;

        if (getId() != null ? !getId().equals(message.getId()) : message.getId() != null) return false;
        if (getSender() != null ? !getSender().equals(message.getSender()) : message.getSender() != null) return false;
        if (getReceiver() != null ? !getReceiver().equals(message.getReceiver()) : message.getReceiver() != null)
            return false;
        if (getContent() != null ? !getContent().equals(message.getContent()) : message.getContent() != null)
            return false;
        return getDateTime() != null ? getDateTime().equals(message.getDateTime()) : message.getDateTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getSender() != null ? getSender().hashCode() : 0);
        result = 31 * result + (getReceiver() != null ? getReceiver().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getDateTime() != null ? getDateTime().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
