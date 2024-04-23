package com.lucaskalita.airlines.seatReservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT * FROM seat o WHERE isReserved = true")
    public List<Seat> findAllReservedSeats();
    @Query("SELECT * FROM seat o WHERE isReserved = false")
    public List<Seat> findAllUnReservedSeats();
    @Query("SELECT * FROM seat o WHERE isPremium = true")
    public List<Seat> findAllPremiumSeats();
    @Query("SELECT * FROM seat o WHERE isPremium = false")
    public List<Seat> findAllRegularSeats();
}
