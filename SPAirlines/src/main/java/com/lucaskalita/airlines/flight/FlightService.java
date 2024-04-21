package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.exceptions.WrongFlightIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {


     private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    public FlightDTO findFlightById(Long id) {
        log.info("Searching for flight by ID: {}", id);
        return flightRepository
                .findById(id)
                .map(flight -> {
                    log.info("Found flight: {}", flight);
                    return flightMapper.fromEntityToDto(flight);
                })
                .orElseThrow(() -> new WrongFlightIDException("No flight with this id: " + id));
    }


    public void deleteFlightById (Long id){
        log.trace("Deleting flight by id: {}", id);
        if (flightRepository.findById(id).isPresent()){
            log.trace("Flight found");
            flightRepository.deleteById(id);
        }else {
            throw new WrongFlightIDException("No flight with this id: " + id);
        }
    }

    public void createNewFlight(FlightDTO flightDTO){
        Flight flight = flightMapper.fromDtoToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        flightMapper.fromEntityToDto(savedFlight);
    }
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new WrongFlightIDException("No flight with this id: " + id));
        FlightDTO flightDTO1 = flightMapper.fromEntityToDto(flight);
        flight.setFlightNumber(flightDTO.flightNumber());
        flight.setDepartureAirport(flightDTO.departureAirport());
        flight.setArrivalAirport(flightDTO.arrivalAirport());
        flight.setDepartureTime(flightDTO.departureTime());
        flight.setArrivalTime(flightDTO.arrivalTime());
        flight.setAvailableTickets(flightDTO.availableTickets());
        flight.setPlaneID(flightDTO.planeID());

        Flight updatedFlight = flightRepository.save(flight);
        return flightMapper.fromEntityToDto(updatedFlight);
    }

    public List<FlightDTO> findFlightsByDepartureDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isAfter(dateTime))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findFlightsByArrivalAirport(Airport arrAirport) {
        log.info("Filtering flights by arrival airport: {}", arrAirport);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getArrivalAirport().equals(arrAirport))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findFlightsByDepartureAirport(Airport depAirport) {
        log.trace("Filtering flights by departure airport: {}", depAirport);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureAirport().equals(depAirport))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findFlightsAfterArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isAfter(dateTime))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<FlightDTO> findFlightsBeforeArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date before: {}", dateTime);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getDepartureTime().isBefore(dateTime))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public Optional<FlightDTO> findFlightByFlightNumber(String flightNumber) {
        log.info("Searching for flight by flight number: {}", flightNumber);
        return flightRepository.findAll()
                .stream()
                .filter(x -> x.getFlightNumber().equals(flightNumber))
                .map(flightMapper::fromEntityToDto)
                .findFirst();
    }
    public List<FlightDTO> findFlightsBetweenDepartureDates (LocalDateTime firstDate, LocalDateTime secondDate){
        log.trace("Searching for flights that departure between {} and {}",firstDate,secondDate);
        return flightRepository.findAll()
                .stream()
                .filter(x->x.getDepartureTime().isAfter(firstDate))
                .filter(x->x.getDepartureTime().isBefore(secondDate))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<FlightDTO> findFlightsBetweenArrivalDates (LocalDateTime firstDate, LocalDateTime secondDate){
        log.trace("Searching for flights that arrives between {} and {}",firstDate,secondDate);
        return flightRepository.findAll()
                .stream()
                .filter(x->x.getArrivalTime().isAfter(firstDate))
                .filter(x->x.getArrivalTime().isBefore(secondDate))
                .map(flightMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

}