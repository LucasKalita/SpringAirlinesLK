package com.lucaskalita.airlines.seatReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
   
     List<Seat> findAllByIsReservedTrue();

     List<Seat> findAllByIsReservedFalse();

     List<Seat> findAllByIsPremiumTrue();

     List<Seat> findAllByIsPremiumFalse();
}
