package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.seatReservation.Seat;
import com.lucaskalita.airlines.users.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private Seat seat;
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket(Long id, String seatNumber, Flight flight, User user) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.flight = flight;
        this.user = user;
    }

    public Ticket() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket ticket)) return false;

        if (getId() != null ? !getId().equals(ticket.getId()) : ticket.getId() != null) return false;
        if (getSeatNumber() != null ? !getSeatNumber().equals(ticket.getSeatNumber()) : ticket.getSeatNumber() != null)
            return false;
        if (getFlight() != null ? !getFlight().equals(ticket.getFlight()) : ticket.getFlight() != null) return false;
        return getUser() != null ? getUser().equals(ticket.getUser()) : ticket.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getSeatNumber() != null ? getSeatNumber().hashCode() : 0);
        result = 31 * result + (getFlight() != null ? getFlight().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber='" + seatNumber + '\'' +
                ", com.lucaskalita.airlines.flight=" + flight +
                ", user=" + user +
                '}';
    }
}