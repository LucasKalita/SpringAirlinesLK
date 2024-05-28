package com.lucaskalita.airlines.flight;
import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.ticket.Ticket;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    private Airport arrivalAirport;
    @OneToMany(mappedBy = "flight")
    private List<Ticket> ticketList;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Duration flightTime;
    private int availableTickets;
    @Column(unique = true)
    private Long planeId;

//TODO stworzyć powiązanie z samolotem oraz listę pasażerów, stowrzyc metode która pobierze lot i sprawdzi ilosc miejsc

    public Flight() {
    }
//TODO coś woła konstruktor flight()
    public Flight(
            Long id,
            String flightNumber,
            Airport departureAirport,
            Airport arrivalAirport,
            List<Ticket> ticketList,
            LocalDateTime departureTime,
            LocalDateTime arrivalTime,
            Duration flightTime,
            int availableTickets,
            Long planeId
    ) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.ticketList = ticketList;
        this.departureTime = departureTime;
        this.arrivalTime = departureTime.plus(flightTime);
        this.flightTime = flightTime;
        this.availableTickets = availableTickets;
        this.planeId = planeId;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureAirport=" + departureAirport +
                ", arrivalAirport=" + arrivalAirport +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", availableTickets=" + availableTickets +
                ", planeID=" + planeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;

        if (getFlightNumber() != null ? !getFlightNumber().equals(flight.getFlightNumber()) : flight.getFlightNumber() != null)
            return false;
        if (getDepartureAirport() != null ? !getDepartureAirport().equals(flight.getDepartureAirport()) : flight.getDepartureAirport() != null)
            return false;
        if (getArrivalAirport() != null ? !getArrivalAirport().equals(flight.getArrivalAirport()) : flight.getArrivalAirport() != null)
            return false;
        if (getDepartureTime() != null ? !getDepartureTime().equals(flight.getDepartureTime()) : flight.getDepartureTime() != null)
            return false;
        if (getArrivalTime() != null ? !getArrivalTime().equals(flight.getArrivalTime()) : flight.getArrivalTime() != null)
            return false;
        return getPlaneId() != null ? getPlaneId().equals(flight.getPlaneId()) : flight.getPlaneId() == null;
    }

    @Override
    public int hashCode() {
        int result = getFlightNumber() != null ? getFlightNumber().hashCode() : 0;
        result = 31 * result + (getDepartureAirport() != null ? getDepartureAirport().hashCode() : 0);
        result = 31 * result + (getArrivalAirport() != null ? getArrivalAirport().hashCode() : 0);
        result = 31 * result + (getDepartureTime() != null ? getDepartureTime().hashCode() : 0);
        result = 31 * result + (getArrivalTime() != null ? getArrivalTime().hashCode() : 0);
        result = 31 * result + (getPlaneId() != null ? getPlaneId().hashCode() : 0);
        return result;
    }

}