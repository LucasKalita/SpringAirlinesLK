package plane;

import exceptions.WrongFlightIDException;
import flight.Flight;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plane.enums.PlaneBrand;
import plane.enums.PlaneModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PlaneService {
    @Autowired
    PlaneRepository planeRepository;

    public Plane findPlanetById(Long id) {
        log.info("Searching for Plane by ID: {}", id);
        return planeRepository
                .findById(id)
                .map(plane -> {
                    log.info("Found flight: {}", plane);
                    return plane;
                })
                .orElseThrow(() -> new WrongFlightIDException("No plane with this id: " + id));
    }
    public List<Plane> findAllPlanes(){
        log.info("Searching for all planes");
        return planeRepository.findAll();
    }
    public List<Plane> findPlanesByBrand (PlaneBrand brand){
        log.info("Search all {} Planes", brand);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getPlaneBrand().equals(brand))
                .collect(Collectors.toList());
    }
    public List<Plane> findPlanesByBrand (PlaneModel model){
        log.info("Search all {} Aircraft", model);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getPlaneModel().equals(model))
                .collect(Collectors.toList());
    }
    public List<Plane> findAllPlanesWithMoreTotalSeatsThan(int x){
        log.info("Searching all planes with more seats than {}", x);
        return planeRepository.findAll()
                .stream()
                .filter(n -> n.getTotalNumberOfSeats() > x)
                .collect(Collectors.toList());
    }

}
