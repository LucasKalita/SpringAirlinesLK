package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

   @PostMapping("/create")
   @ResponseStatus(HttpStatus.CREATED)
   public Long createTicket(@RequestBody TicketDTO ticketDTO){
       return  ticketService.createTicket(ticketDTO);
   }

    @GetMapping("/departure-airport/{airportCode}")
    public ResponseEntity<List<TicketDTO>> getTicketsForFlightsByDepartureAirport(@PathVariable Airport departureAirport) {
        List<TicketDTO> tickets = ticketService.findAllTicketsForFlightsByDepartureAirport(departureAirport);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/arrival-airport/{airportCode}")
    public ResponseEntity<List<TicketDTO>> getTicketsForFlightsByArrivalAirport(@PathVariable Airport arrivalAirport) {
        List<TicketDTO> tickets = ticketService.findAllTicketsByArrivalAirport(arrivalAirport);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/user/{userId}/flight/{flightNumber}")
    public ResponseEntity<List<TicketDTO>> getUserTicketsByFlightNumberAndUser(@PathVariable User user,
                                                                        @PathVariable String flightNumber) {
        List<TicketDTO> tickets = ticketService.findUserTicketsByFlightNumber(flightNumber, user);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<List<TicketDTO>> getTicketsByFlightNumber(@PathVariable String flightNumber) {
        List<TicketDTO> tickets = ticketService.findTicketByFlightNumber(flightNumber);
        return ResponseEntity.ok(tickets);
    }
}
