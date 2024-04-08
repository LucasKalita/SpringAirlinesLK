package flight;

import plane.Plane;
import airport.Airport;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightNumber;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableTickets;
    @ManyToOne
    private plane.Plane plane;

    public Flight(Long id,
                  String flightNumber,
                  Airport departureAirport,
                  Airport arrivalAirport,
                  LocalDateTime departureTime,
                  LocalDateTime arrivalTime,
                  int availableTickets,
                  Plane plane) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableTickets = availableTickets;
        this.plane = plane;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(airport.Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public airport.Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(airport.Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public plane.Plane getPlane() {
        return plane;
    }

    public void setPlane(plane.Plane plane) {
        this.plane = plane;
    }


    public String getFlightNumber() {
        return this.flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}