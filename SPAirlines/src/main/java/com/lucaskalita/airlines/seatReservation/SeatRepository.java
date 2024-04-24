package com.lucaskalita.airlines.seatReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
   
    public List<Seat> findAllByIsReservedTrue();

    public List<Seat> findAllByIsReservedFalse();

    public List<Seat> findAllByIsPremiumTrue();

    public List<Seat> findAllByIsPremiumFalse();
}
