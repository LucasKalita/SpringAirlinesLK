package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
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
    public Long createPlane(PlaneDTO planeDTO){
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
                ()->{
            throw new WrongObjectIdException("No object by this id: " + id);
                });
    }
    public PlaneDTO findByFlightNumber(String flightNumber) {
        return planeRepository.findByFlightNumber(flightNumber)
                .map(plane -> {
                    log.trace("Flight found");
                    return planeMapper.fromEntityToDto(plane);
                })
                .orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + flightNumber));
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
    public List<PlaneDTO> findPlanesByModel (String model){
        log.info("Search all {} Aircraft", model);
        return planeRepository.findAllByPlaneModel(model)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }


}
