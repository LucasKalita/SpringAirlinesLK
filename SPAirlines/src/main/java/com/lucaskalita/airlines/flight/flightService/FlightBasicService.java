package com.lucaskalita.airlines.flight.flightService;

import com.lucaskalita.airlines.airport.AirportMapper;
import com.lucaskalita.airlines.airport.AirportRepository;
import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.flight.FlightRepository;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.ticket.TicketMapper;
import com.lucaskalita.airlines.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FlightBasicService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportMapper airportMapper;
    private final AirportRepository airportRepository;
    private final TicketMapper ticketMapper;

    public FlightDTO findFlightById(Long id) {
        log.info("Searching for flight by ID: {}", id);
        return flightRepository
                .findById(id)
                .map(flight -> {
                    log.info("Found flight: {}", flight);
                    return flightMapper.fromEntityToDto(flight);
                })
                .orElseThrow(() -> new WrongObjectIdException("No flight with this id: " + id));
    }

    public void deleteFlightById(Long id) {
        log.trace("Deleting flight by id: {}", id);
        if (flightRepository.findById(id).isPresent()) {
            log.trace("Flight found");
            flightRepository.deleteById(id);
        } else {
            throw new WrongObjectIdException("No flight with this id: " + id);
        }
    }

    public FlightDTO createNewFlight(FlightDTO flightDTO) {
        log.trace("Creating new Flight");
        Flight flight = flightMapper.fromDtoToEntity(flightDTO);
        airportRepository.findByAirportCode(flight.getArrivalAirport().getAirportCode()).ifPresent(flight::setArrivalAirport);
        airportRepository.findByAirportCode(flight.getDepartureAirport().getAirportCode()).ifPresent(flight::setDepartureAirport);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.fromEntityToDto(savedFlight);
    }

    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setFlightNumber(flightDTO.flightNumber());
                    flight.setDepartureAirport(airportMapper.fromDtoToEntity(flightDTO.departureAirport()));
                    flight.setArrivalAirport(airportMapper.fromDtoToEntity(flightDTO.arrivalAirport()));
                    flight.setDepartureTime(flightDTO.departureTime());
                    flight.setArrivalTime(flightDTO.arrivalTime());
                    flight.setFlightNumber(flightDTO.flightNumber());
                    Flight updatedFlight = flightRepository.save(flight);
                    return flightMapper.fromEntityToDto(updatedFlight);
                })
                .orElseThrow(() -> new WrongObjectIdException("No object by this id: " + id));
    }
    public List<TicketDTO> getTicketList(String flightNumber){
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow(()->new WrongObjectIdException("Wrong id"));

        return flight.getTicketList().stream().map(ticketMapper::fromEntityToDto).toList();
    }
}
