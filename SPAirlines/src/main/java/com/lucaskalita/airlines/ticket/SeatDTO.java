package com.lucaskalita.airlines.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {
    Set<Integer> availableRegularSeats;
    Set<Integer> availablePremiumSeats;
}
