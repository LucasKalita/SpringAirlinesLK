package ticket;

import airport.Airport;
import exceptions.WrongFlightIDException;
import exceptions.WrongTicketIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    public Ticket findTicketByID (Long id){
        log.info("Searching for ticket with id: {}", id);
        return ticketRepository.findById(id).map(ticket -> {
            log.info("Found ticket with this id:{}",ticket);
            return ticket;
        }).orElseThrow(() -> new WrongTicketIDException("No ticket with this id: " + id));

    }
    public List<Ticket> findAllUserTickets (User user){
        log.info("Searching for all user's tickets");
        return ticketRepository.findAll()
                .stream()
                .filter(n->n.getUser().equals(user))
                .collect(Collectors.toList());
    }
    public List<Ticket> findAllTicketsForFlightsByDepartureAirport(Airport airport){
        log.info("Filtering tickets by {} airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .collect(Collectors.toList());
    }
    public List<Ticket>findAllTicketsForFlightsByArrivalAirport(Airport airport){
        log.info("Filtering tickets by {} airport", airport);
        return  ticketRepository.findAll()
                .stream()
                .filter(n->n.getFlight().getDepartureAirport().equals(airport))
                .collect(Collectors.toList());
    }
    public Optional<Ticket> findTicketByFlightNumber(String flightNumber, User user) {
        log.info("Searching for ticket for flight {} and user {}", flightNumber, user.getUsername());
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .filter(ticket -> ticket.getFlight().getFlightNumber().equals(flightNumber))
                .findFirst();
    }
}
