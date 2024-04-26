package com.lucaskalita.airlines.airport;

import com.lucaskalita.airlines.exceptions.AirportNotFoundException;
import com.lucaskalita.airlines.exceptions.WrongAirportIDException;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AirportService {

    private final AirportRepository airportRepository;

    private final AirportMapper airportMapper;

    public AirportDTO getAirportById(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new WrongAirportIDException("Airport not found with id: " + id));

        return airportMapper.fromEntityToDto(airport);
    }

    public void deleteAirport(Long id) {
        log.trace("Deleting airport by it's id: {}", id);
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new RuntimeException("Airport not found with id: " + id);
        }
    }

    public AirportDTO addAirport(AirportDTO airportDTO) {
        log.trace("Creating new Airport");
        Airport airport = airportMapper.fromDtoToEntity(airportDTO);
        Airport savedAirport = airportRepository.save(airport);

        return airportMapper.fromEntityToDto(savedAirport);
    }

    public List<AirportDTO> findAllAirportsInCountry(Country country) {
        log.trace("Searching for all airports in {}", country);
        return airportRepository.findAllByCountry(country)
                .stream()
                .map(airportMapper::fromEntityToDto)
                .toList();
    }

    public AirportDTO findAirportByAirportCode(String code) {
        log.trace("Searching for airport by code: {}", code);

        Airport airport = airportRepository.findByAirportCode(code);

        if (airport == null) {
            log.warn("Airport with code {} not found", code);
            throw new AirportNotFoundException("Airport with code " + code + " not found");
        }

        AirportDTO airportDTO = airportMapper.fromEntityToDto(airport);
        log.trace("Found airport: {}", airportDTO);

        return airportDTO;
    }

}