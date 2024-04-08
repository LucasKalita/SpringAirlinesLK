package Messages;

import users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
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

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Message() {
    }

    public Message(Long id, User sender, User receiver, String content, LocalDateTime dateTime) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.dateTime = dateTime;
    }

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