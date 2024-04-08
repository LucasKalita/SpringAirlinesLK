package flight;

import airport.Airport;
import exceptions.WrongFlightIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight findFlightById(Long id) {
        log.info("Searching for flight by ID: {}", id);
        return flightRepository
                .findById(id)
                .map(flight -> {
                    log.info("Found flight: {}", flight);
                    return flight;
                })
                .orElseThrow(() -> new WrongFlightIDException("No flight with this id: " + id));
    }

    public List<Flight> findAllFlights() {
        log.info("Fetching all flights");
        List<Flight> flights = flightRepository.findAll();
        log.info("Number of found flights: {}", flights.size());
        log.debug("All flights: {}", flights);
        return flights;
    }

    public List<Flight> findFlightsByDepartureDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isAfter(dateTime))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightsByArrivalAirport(Airport arrAirport) {
        log.info("Filtering flights by arrival airport: {}", arrAirport);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getArrivalAirport().equals(arrAirport))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightsByDepartureAirport(Airport depAirport) {
        log.trace("Filtering flights by departure airport: {}", depAirport);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureAirport().equals(depAirport))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightsAfterArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isAfter(dateTime))
                .collect(Collectors.toList());
    }

    public List<Flight> findFlightsBeforeArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date before: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isBefore(dateTime))
                .collect(Collectors.toList());
    }

    public Optional<Flight> findFlightByFlightNumber(String flightNumber) {
        log.info("Searching for flight by flight number: {}", flightNumber);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getFlightNumber().equals(flightNumber))
                .findFirst();
    }

}