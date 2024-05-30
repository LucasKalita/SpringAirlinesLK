package com.lucaskalita.airlines.flight;

import com.lucaskalita.airlines.airport.Airport;
import com.lucaskalita.airlines.airport.AirportDTO;
import com.lucaskalita.airlines.airport.AirportMapper;
import com.lucaskalita.airlines.airport.AirportService;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.plane.Plane;
import com.lucaskalita.airlines.plane.PlaneMapper;
import com.lucaskalita.airlines.plane.PlaneService;
import com.lucaskalita.airlines.ticket.Ticket;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final PlaneService planeService;
    private final PlaneMapper planeMapper;
    private final AirportMapper airportMapper;
    private final AirportService airportService;

    public FlightDTO findFlightById(Long id) {
        log.info("Searching for flight by ID: {}", id);
        return flightRepository
                .findById(id)
                .map(flight -> {
                    log.info("Found flight: {}", flight);
                    return flightMapper.fromEntityToDto(flight);
                })
                .orElseThrow(() -> new WrongObjectIdException("No flight with this id: " + id));
    }

    public void deleteFlightById(Long id) {
        log.trace("Deleting flight by id: {}", id);
        if (flightRepository.findById(id).isPresent()) {
            log.trace("Flight found");
            flightRepository.deleteById(id);
        } else {
            throw new WrongObjectIdException("No flight with this id: " + id);
        }
    }

    public void createNewFlight(FlightDTO flightDTO) {
        Flight flight = flightMapper.fromDtoToEntity(flightDTO);
        Flight savedFlight = flightRepository.save(flight);
        flightMapper.fromEntityToDto(savedFlight);
    }

    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) {
        return flightRepository.findById(id)
                .map(flight -> {
                    flight.setFlightNumber(flightDTO.flightNumber());
                    flight.setDepartureAirport(airportMapper.fromDtoToEntity(flightDTO.departureAirport()));
                    flight.setArrivalAirport(airportMapper.fromDtoToEntity(flightDTO.arrivalAirport()));
                    flight.setDepartureTime(flightDTO.departureTime());
                    flight.setArrivalTime(flightDTO.arrivalTime());
                    flight.setFlightNumber(flightDTO.flightNumber());
                    Flight updatedFlight = flightRepository.save(flight);
                   return flightMapper.fromEntityToDto(updatedFlight);
                })
                .orElseThrow(() -> new WrongObjectIdException("No object by this id: " + id));
    }

    public FlightDTO findFlightByPlaneId (Long planeId){
       return flightRepository.findByPlaneId(planeId)
                .map( flightMapper::fromEntityToDto)
                .orElseThrow(()->new WrongObjectIdException("No object by this id: "+planeId));
    }
    public String checkForEmptySeats(Long id) {
        return flightRepository.findById(id)
                .map(flight -> "Premium seats available: " + premiumTicketsAvailability(flight) +
                        " Regular Seats Available: " + regularTicketsAvailability(flight))
                .orElseThrow(() -> new WrongObjectIdException("No object by this id"));
    }
    private int premiumTicketsAvailability(Flight flight){
         int tickets = flight.getTicketList().stream().filter(Ticket::isPremium).toList().size();
         Plane plane = planeMapper.fromDtoToEntity(planeService.findPlaneById(flight.getPlaneId()));
        int premiumSeats = plane.getPremiumSeats();
        return premiumSeats - tickets;

    }
    private int regularTicketsAvailability(Flight flight){
        int ticketsAmount = flight.getTicketList().stream().filter(ticket -> !ticket.isPremium()).toList().size();
        Plane plane = planeMapper.fromDtoToEntity(planeService.findPlaneById(flight.getPlaneId()));
        int regularSeats = plane.getRegularSeats();
        return regularSeats - ticketsAmount;
    }

    public List<FlightDTO> findFlightsByDepartureDateAfter(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAllByDepartureTimeAfter(dateTime)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

    public List<FlightDTO> findFlightsByDepartureDateBefore(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date before: {}", dateTime);
        return flightRepository.findAllByDepartureTimeBefore(dateTime)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsByDepartureDateBetween(LocalDateTime dateTime, LocalDateTime dateTime2) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAllByDepartureTimeBetween(dateTime, dateTime2)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
//////////////// arrival date

    public List<FlightDTO> findFlightsAfterArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date after: {}", dateTime);
        return flightRepository.findAllByArrivalTimeAfter(dateTime)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBeforeArrivalDate(LocalDateTime dateTime) {
        log.info("Filtering flights by departure date before: {}", dateTime);
        return flightRepository.findAllByArrivalTimeBefore(dateTime)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenArrivalDates(LocalDateTime firstDate, LocalDateTime secondDate) {
        log.trace("Searching for flights that arrives between {} and {}", firstDate, secondDate);
        return flightRepository.findAllByArrivalTimeBetween(firstDate, secondDate)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

/////////////// airports

    public List<FlightDTO> findFlightsByArrivalAirport(AirportDTO airportDTO) {
        log.info("Filtering flights by arrival airport: {}", airportDTO);
        Airport arrAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByArrivalAirport(arrAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }

    public List<FlightDTO> findFlightsByDepartureAirport(AirportDTO airportDTO) {
        log.trace("Filtering flights by departure airport: {}", airportDTO);
        Airport depAirport = airportMapper.fromDtoToEntity(airportDTO);
        return flightRepository.findAllByDepartureAirport(depAirport)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenAirports(AirportDTO depAirport, AirportDTO arrAirport) {
        log.trace("Filtering flights connected between {} and {}", depAirport, arrAirport);
        Airport depAirport1 = airportMapper.fromDtoToEntity(depAirport);
        Airport arrAirport1 = airportMapper.fromDtoToEntity(arrAirport);
        return flightRepository.findAllByDepartureAirportAndArrivalAirport(depAirport1, arrAirport1)
                .stream()
                .map(flightMapper::fromEntityToDto)
                .toList();
    }
    public List<FlightDTO> findFlightsBetweenCities(String departureAirportCity, String arrivalAirportCity){
        log.trace("Searching for flight between city " + departureAirportCity + " and " + arrivalAirportCity);
        AirportDTO departureAirport = airportService.findAirportByCity(departureAirportCity);
        AirportDTO arrivalAirport = airportService.findAirportByCity(arrivalAirportCity);
        return findFlightsBetweenAirports(departureAirport, arrivalAirport);
    }
    //TODO ogarnąc to gówno nizej, wywalić szukanie po czystych airportach
    public List<FlightDTO> findFlightsBetweenCountries(Country departureCountry, Country arrivalCountry){
        log.trace("Searching for flight between country " + departureCountry + " and " + arrivalCountry);
     //   AirportDTO airportDTO1 = airportService.findAllAirportsInCountry(departureCountry, arrivalCountry);
        return  null;
    }


}