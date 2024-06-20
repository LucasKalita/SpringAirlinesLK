package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String surname;
    private BigDecimal price;
    private String seatNumber;
    private String ticketNumber;
    private boolean isPremium;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;

        if (isPremium() != ticket.isPremium()) return false;
        if (getId() != null ? !getId().equals(ticket.getId()) : ticket.getId() != null) return false;
        if (getUser() != null ? !getUser().equals(ticket.getUser()) : ticket.getUser() != null) return false;
        if (getName() != null ? !getName().equals(ticket.getName()) : ticket.getName() != null) return false;
        if (getSurname() != null ? !getSurname().equals(ticket.getSurname()) : ticket.getSurname() != null)
            return false;
        if (getPrice() != null ? !getPrice().equals(ticket.getPrice()) : ticket.getPrice() != null) return false;
        if (getSeatNumber() != null ? !getSeatNumber().equals(ticket.getSeatNumber()) : ticket.getSeatNumber() != null)
            return false;
        return getTicketNumber() != null ? getTicketNumber().equals(ticket.getTicketNumber()) : ticket.getTicketNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (getSeatNumber() != null ? getSeatNumber().hashCode() : 0);
        result = 31 * result + (getTicketNumber() != null ? getTicketNumber().hashCode() : 0);
        result = 31 * result + (isPremium() ? 1 : 0);
        return result;
    }
}