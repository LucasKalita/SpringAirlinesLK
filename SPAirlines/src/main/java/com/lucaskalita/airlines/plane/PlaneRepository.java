package com.lucaskalita.airlines.plane;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PlaneRepository extends JpaRepository<Plane, Long> {
    List<Plane> findAllByPlaneBrand(String brand);
    List<Plane> findAllByPlaneModel(String model);
    Optional<Plane> findByPlaneSerialNumber(String planeSerialNumber);


}

