package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.plane.Plane;
import com.lucaskalita.airlines.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    private boolean isReserved;
    private boolean isPremium;
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;

}
