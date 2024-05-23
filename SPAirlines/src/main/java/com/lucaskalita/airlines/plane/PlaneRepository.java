package com.lucaskalita.airlines.plane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
    List<Plane> findAllByPlaneBrand(String brand);

    List<Plane> findAllByPlaneModel(String model);

    @Query("SELECT p FROM Plane p WHERE" + " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = false) > :amount")
    List<Plane> findByListOfRegularSeatsSizeGreaterThan(int amount);

    @Query("SELECT p FROM Plane p WHERE" + " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = false) < :amount")
    List<Plane> findByListOfRegularSeatsSizeLessThan(int amount);

    @Query("SELECT p FROM Plane p WHERE" + " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = true) > :amount")
    List<Plane> findByListOfPremiumSeatsSizeGreaterThan(int amount);

    @Query("SELECT p FROM Plane p WHERE" + " (SELECT COUNT(s) FROM Seat s WHERE s.plane = p AND s.isPremium = true) < :amount")
    List<Plane> findByListOfPremiumSeatsSizeLessThan(int amount);

    List<Plane> findByTotalSeatsSizeGreaterThan(int value);

    List<Plane> findByTotalSeatsSizeLessThan(int value);
}

