package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.exceptions.WrongFlightIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlaneService {
    @Autowired
    PlaneRepository planeRepository;
    @Autowired
    PlaneMapper planeMapper;

    public PlaneDTO findPlanetById(Long id) {
        log.info("Searching for Plane by ID: {}", id);
        return planeRepository
                .findById(id)
                .map(plane -> {
                    log.info("Aircraft found: {}", plane);
                    return planeMapper.fromEntityToDto(plane);
                })
                .orElseThrow(() -> new WrongFlightIDException("No plane with this id: " + id));
    }
    public List<PlaneDTO> findAllPlanes(){
        log.info("Searching for all planes");
        return planeRepository.findAll()
                .stream()
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findPlanesByBrand (PlaneBrand brand){
        log.info("Search all {} Planes", brand);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getPlaneBrand().equals(brand))
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findPlanesByBrand (PlaneModel model){
        log.info("Search all {} Aircraft", model);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getPlaneModel().equals(model))
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfSeats(int x){
        log.info("Searching all planes with more seats than {}", x);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getListOfPremiumSeats().size() + n.getListOfRegularSeats().size() >= x)
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findAllPlanesWithMaximalNumberOfSeats(int x){
        log.info("Searching all planes with maximal seats amount: {}", x);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getListOfPremiumSeats().size() + n.getListOfRegularSeats().size() <= x)
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfRegularSeats(int x){
        log.info("Searching all planes with minimal regular seats amount: {}", x);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getListOfRegularSeats().size() >= x)
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfPremiumSeats(int x){
        log.info("Searching all planes with minimal premium seats amount: {}", x);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getListOfPremiumSeats().size() >= x)
                .map(planeMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

}
