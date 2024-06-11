package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.airport.AirportRepository;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.plane.enums.PlaneStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlaneService {

    private final PlaneRepository planeRepository;
    private final PlaneMapper planeMapper;
    private  final AirportRepository airportRepository;

    //TODO podzielic service
    public PlaneDTO findPlaneById(Long id) {
        log.info("Searching for Plane by ID: {}", id);
        return planeRepository
                .findById(id)
                .map(plane -> {
                    log.info("Aircraft found: {}", plane);
                    return planeMapper.fromEntityToDto(plane);
                })
                .orElseThrow(() -> new WrongObjectIdException("No plane with this id: " + id));
    }

    public Long createPlane(PlaneDTO planeDTO) {
        log.info("Adding a new plane");
        Plane plane = planeMapper.fromDtoToEntity(planeDTO);
        planeRepository.save(plane);

        return plane.getId();
    }

    public void deletePlaneById(Long id) {
        log.info("Deleting plane with id: {}", id);
        planeRepository.findById(id).ifPresentOrElse(plane -> {
                    log.trace("Plane found, deleting");
                    planeRepository.deleteById(id);
                },
                () -> {
                    throw new WrongObjectIdException("No object by this id: " + id);
                });
    }

    public PlaneDTO findByPlaneSerialNumber(String planeSerialNumber) {
        return planeRepository.findByPlaneSerialNumber(planeSerialNumber)
                .map(plane -> {
                    log.trace("Flight found");
                    return planeMapper.fromEntityToDto(plane);
                })
                .orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + planeSerialNumber));
    }

    public List<PlaneDTO> findPlanesByPlaneStatus(PlaneStatus planeStatus) {
        return planeRepository.findAllByPlaneStatus(planeStatus)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public PlaneDTO updatePlaneStatus(PlaneStatus planeStatus, String planeSerialNumber){
       Plane plane1 =  planeRepository.findByPlaneSerialNumber(planeSerialNumber)
                .map(plane -> {
                    log.trace("Flight found");
                    plane.setPlaneStatus(planeStatus);
                    planeRepository.save(plane);
                    return plane;
                })
                .orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + planeSerialNumber));

       return planeMapper.fromEntityToDto(plane1);
    }

    public List<PlaneDTO> findPlanesByStatusOnAirfield(PlaneStatus planeStatus, String airportCode){
        Airport airport = airportRepository.findByAirportCode(airportCode)
                .orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + airportCode));

        return planeRepository.findAllByHangarAirport(airport)
                .stream()
                .filter(plane -> plane.getPlaneStatus().equals(planeStatus))
                .map(planeMapper::fromEntityToDto)
                .toList();
    }

    public PlaneDTO updatePlane(Long id, PlaneDTO planeDTO) {
        log.info("Updating plane with id: {}", id);

        Plane updatedPlane = planeRepository.findById(id)
                .map(plane -> {
                    Plane mappedPlane = planeMapper.fromDtoToEntity(planeDTO);
                    mappedPlane.setId(plane.getId());
                    mappedPlane.setPlaneBrand(plane.getPlaneBrand());
                    mappedPlane.setPlaneModel(plane.getPlaneModel());
                    mappedPlane.setRegularSeats(plane.getRegularSeats());
                    mappedPlane.setPremiumSeats(plane.getPremiumSeats());
                    return planeRepository.save(mappedPlane);
                })
                .orElseThrow(() -> new WrongObjectIdException("Plane not found with id: " + id));

        return planeMapper.fromEntityToDto(updatedPlane);
    }

    //TODO przepisać metody szukajace plane po ilosci siedzen
    public List<PlaneDTO> findPlanesByBrand(String brand) {
        log.info("Searching for planes of brand: {}", brand);

        return planeRepository.findAllByPlaneBrand(brand)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }

    public List<PlaneDTO> findPlanesByModel(String model) {
        log.info("Search all {} Aircraft", model);
        return planeRepository.findAllByPlaneModel(model)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }


}
