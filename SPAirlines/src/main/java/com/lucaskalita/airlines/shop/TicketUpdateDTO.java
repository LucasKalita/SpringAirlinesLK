package com.lucaskalita.airlines.shop;


import lombok.Builder;

@Builder
public record TicketUpdateDTO(
        String seatNumber,
        String name,
        String surname,
        Boolean isPremium

) {
}
