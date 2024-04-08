package seatReservation;

import flight.Flight;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;
import org.springframework.data.annotation.Id;
import plane.Plane;
import ticket.Ticket;
import users.User;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   private boolean isReserved;


}
