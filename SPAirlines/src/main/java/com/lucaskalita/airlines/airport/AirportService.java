package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    public AirportDTO getAirportById(Long id) {
        Airport airport = airportRepository.findById(id).orElseThrow(() -> new WrongObjectIdException("Airport not found with id: " + id));

        return airportMapper.fromEntityToDto(airport);
    }

    public void deleteAirport(Long id) {
        log.trace("Deleting airport by it's id: {}", id);
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new WrongObjectIdException("Airport not found with id: " + id);
        }
    }

    public Long createAirport(AirportDTO airportDTO) {
        log.trace("Creating new Airport");
        Airport airport = airportMapper.fromDtoToEntity(airportDTO);
        airportRepository.save(airport);
        return airport.getId();
    }

    public List<AirportDTO> findAllAirportsInCountry(Country country) {
        log.trace("Searching for all airports in {}", country);
        return airportRepository.findAllByCountry(country).stream().map(airportMapper::fromEntityToDto).toList();
    }

    public AirportDTO findAirportByAirportCode(String code) {
        log.trace("Searching for airport by code: {}", code);
        return airportRepository.findByAirportCode(code).map(airport -> {
            log.trace("Airport found");
            return airportMapper.fromEntityToDto(airport);
        }).orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + code));
    }

    public AirportDTO findAirportByCity(String city) {
        log.trace("Searching for Airport in :" + city);
        return airportRepository.findByCity(city).map(airport -> {
            log.trace("AirportFound");
            return airportMapper.fromEntityToDto(airport);
        }).orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + city));
    }

}