package com.lucaskalita.airlines.plane;

import com.lucaskalita.airlines.exceptions.WrongPlaneIDException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.lucaskalita.airlines.plane.enums.PlaneBrand;
import com.lucaskalita.airlines.plane.enums.PlaneModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PlaneService {

   private final PlaneRepository planeRepository;

   private final PlaneMapper planeMapper;

    public PlaneDTO findPlanetById(Long id) {
        log.info("Searching for Plane by ID: {}", id);
        return planeRepository
                .findById(id)
                .map(plane -> {
                    log.info("Aircraft found: {}", plane);
                    return planeMapper.fromEntityToDto(plane);
                })
                .orElseThrow(() -> new WrongPlaneIDException("No plane with this id: " + id));
    }
    public void addPlane(PlaneDTO planeDTO){
        log.info("Adding a new plane");
        Plane plane = planeMapper.fromDtoToEntity(planeDTO);
        Plane savedPlane = planeRepository.save(plane);
        planeMapper.fromEntityToDto(savedPlane);
    }
    public void deletePlaneById(Long id) {
        log.info("Deleting plane with id: {}", id);

        if (planeRepository.existsById(id)) {
            planeRepository.deleteById(id);
        } else {
            throw new WrongPlaneIDException("No plane found with id: " + id);
        }
    }
    public PlaneDTO updatePlane(Long id, PlaneDTO planeDTO) {
        log.info("Updating plane with id: {}", id);

        Plane updatedPlane = planeRepository.findById(id)
                .map(plane -> {
                    Plane mappedPlane = planeMapper.fromDtoToEntity(planeDTO);
                    mappedPlane.setId(plane.getId());
                    mappedPlane.setPlaneBrand(plane.getPlaneBrand());
                    mappedPlane.setPlaneModel(plane.getPlaneModel());
                    mappedPlane.setListOfPremiumSeats(plane.getListOfPremiumSeats());
                    mappedPlane.setListOfRegularSeats(plane.getListOfRegularSeats());
                    return planeRepository.save(mappedPlane);
                })
                .orElseThrow(() -> new WrongPlaneIDException("Plane not found with id: " + id));

        return planeMapper.fromEntityToDto(updatedPlane);
    }

    public List<PlaneDTO> findPlanesByBrand(PlaneBrand brand) {
        log.info("Searching for planes of brand: {}", brand);

        return planeRepository.findAllByPlaneBrand(brand)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findPlanesByModel (PlaneModel model){
        log.info("Search all {} Aircraft", model);
        return planeRepository.findAllByPlaneModel(model)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfSeats(int x){
        log.info("Searching all planes with more seats than {}", x);
        return planeRepository.findByTotalSeatsSizeGreaterThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMaximalNumberOfSeats(int x){
        log.info("Searching all planes with less seats than: {}", x);
        return planeRepository.findByTotalSeatsSizeLessThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfRegularSeats(int x){
        log.info("Searching all planes with minimal regular seats amount: {}", x);
        return planeRepository.findByListOfRegularSeatsSizeGreaterThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMaximalNumberOfRegularSeats(int x){
        log.info("Searching all planes with minimal regular seats amount: {}", x);
        return planeRepository.findByListOfRegularSeatsSizeLessThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMinimalNumberOfPremiumSeats(int x){
        log.info("Searching all planes with minimal premium seats amount: {}", x);
        return planeRepository.findByListOfPremiumSeatsSizeGreaterThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }
    public List<PlaneDTO> findAllPlanesWithMaximalNumberOfPremiumSeats(int x){
        log.info("Searching all planes with minimal premium seats amount: {}", x);
        return planeRepository.findByListOfPremiumSeatsSizeLessThan(x)
                .stream()
                .map(planeMapper::fromEntityToDto)
                .toList();
    }

}
