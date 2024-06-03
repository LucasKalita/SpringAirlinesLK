package com.lucaskalita.airlines.shop;

import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
private UserService userService;
private StoreService storeService;
    @PutMapping("/refundTicket/{ticketNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void refundTicket(@PathVariable String ticketNumber) {
        storeService.refundTicket(ticketNumber);
    }
    @PutMapping("/buyTicket/{flightId}/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public TicketDTO buyTicket(@RequestBody SeatDetailsDTO seatDetailsDTO, @PathVariable String username, @PathVariable Long flightId ){
       return storeService.buyTicket(username, flightId, seatDetailsDTO);
    }
}
