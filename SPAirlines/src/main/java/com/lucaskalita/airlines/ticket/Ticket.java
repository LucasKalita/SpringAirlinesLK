package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.seatReservation.Seat;
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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String username;
    private String name;
    private String surname;
    private BigDecimal price;
    private String seatNumber;
    @Column( columnDefinition = "Varchar(6)")
    private String ticketNumber;
    private boolean isPremium;
}