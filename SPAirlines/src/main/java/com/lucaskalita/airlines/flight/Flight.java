package com.lucaskalita.airlines.flight;
import com.lucaskalita.airlines.airport.Airport;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableTickets;
    @Column(unique = true)
    private Long planeID;

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
                ", planeID=" + planeID +
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
        return getPlaneID() != null ? getPlaneID().equals(flight.getPlaneID()) : flight.getPlaneID() == null;
    }

    @Override
    public int hashCode() {
        int result = getFlightNumber() != null ? getFlightNumber().hashCode() : 0;
        result = 31 * result + (getDepartureAirport() != null ? getDepartureAirport().hashCode() : 0);
        result = 31 * result + (getArrivalAirport() != null ? getArrivalAirport().hashCode() : 0);
        result = 31 * result + (getDepartureTime() != null ? getDepartureTime().hashCode() : 0);
        result = 31 * result + (getArrivalTime() != null ? getArrivalTime().hashCode() : 0);
        result = 31 * result + (getPlaneID() != null ? getPlaneID().hashCode() : 0);
        return result;
    }
}