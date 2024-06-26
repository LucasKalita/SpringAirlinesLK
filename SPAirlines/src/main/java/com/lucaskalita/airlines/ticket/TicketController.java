package com.lucaskalita.airlines.ticket;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.shop.PriceDTO;
import com.lucaskalita.airlines.shop.TicketUpdateDTO;
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
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
        public void deleteTicketById(@PathVariable Long id){
        ticketService.deleteTicketById(id);
    }
    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public TicketDTO findTicketById(@PathVariable Long id){
       return ticketService.findTicketByID(id);
    }
    @GetMapping("/findByTicketNumber")
    @ResponseStatus(HttpStatus.FOUND)
    public TicketDTO findByTicketNumber(String number){
       return ticketService.findTicketByTicketNumber(number);
    }

    @PutMapping("/changeTicketPrice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void changeTicketPrice(@PathVariable Long id, @RequestBody PriceDTO priceDTO){
        ticketService.changeTicketPrice(id, priceDTO);
    }

    @GetMapping("/departure-airport/{airportCode}")
    public List<TicketDTO> getTicketsForFlightsByDepartureAirport(@PathVariable Airport departureAirport) {
        return ticketService.findAllTicketsForFlightsByDepartureAirport(departureAirport);
    }

    @GetMapping("/arrival-airport/{airportCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<TicketDTO> getTicketsForFlightsByArrivalAirport(@PathVariable Airport arrivalAirport) {
        return ticketService.findAllTicketsByArrivalAirport(arrivalAirport);
    }

    @GetMapping("/flight/{flightNumber}")
    public ResponseEntity<List<TicketDTO>> getTicketsByFlightNumber(@PathVariable String flightNumber) {
        List<TicketDTO> tickets = ticketService.findTicketByFlightNumber(flightNumber);
        return ResponseEntity.ok(tickets);
    }
}
