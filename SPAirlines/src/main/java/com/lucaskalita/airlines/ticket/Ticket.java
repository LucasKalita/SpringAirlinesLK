package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.seatReservation.Seat;
import com.lucaskalita.airlines.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String seatNumber;
    private Seat seat;
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private User user;
    private String name;
    private  String surname;


}