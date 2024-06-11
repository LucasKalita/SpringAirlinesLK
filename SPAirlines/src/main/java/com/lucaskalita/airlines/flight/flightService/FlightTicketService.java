package com.lucaskalita.airlines.flight.flightService;
import com.lucaskalita.airlines.airport.*;
import com.lucaskalita.airlines.flight.Flight;
import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.flight.FlightRepository;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.plane.Plane;
import com.lucaskalita.airlines.plane.PlaneMapper;
import com.lucaskalita.airlines.plane.PlaneService;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FlightTicketService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final PlaneService planeService;
    private final PlaneMapper planeMapper;
    private final AirportMapper airportMapper;
    private final AirportRepository airportRepository;
    private final AirportService airportService;

    public String checkForEmptySeats(Long id) {
        return flightRepository.findById(id)
                .map(flight -> "Premium seats available: " + premiumTicketsAvailability(flight) +
                        " Regular Seats Available: " + regularTicketsAvailability(flight))
                .orElseThrow(() -> new WrongObjectIdException("No object by this id"));
    }
    private int premiumTicketsAvailability(Flight flight){
        int tickets = flight.getTicketList().stream().filter(Ticket::isPremium).toList().size();
        Plane plane = planeMapper.fromDtoToEntity(planeService.findPlaneById(flight.getPlaneId()));
        int premiumSeats = plane.getPremiumSeats();
        return premiumSeats - tickets;

    }
    private int regularTicketsAvailability(Flight flight){
        int ticketsAmount = flight.getTicketList().stream().filter(ticket -> !ticket.isPremium()).toList().size();
        Plane plane = planeMapper.fromDtoToEntity(planeService.findPlaneById(flight.getPlaneId()));
        int regularSeats = plane.getRegularSeats();
        return regularSeats - ticketsAmount;
    }
}
