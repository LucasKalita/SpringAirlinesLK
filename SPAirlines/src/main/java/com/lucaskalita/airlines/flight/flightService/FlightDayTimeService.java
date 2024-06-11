package com.lucaskalita.airlines.flight.flightService;
import com.lucaskalita.airlines.flight.FlightDTO;
import com.lucaskalita.airlines.flight.FlightMapper;
import com.lucaskalita.airlines.flight.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FlightDayTimeService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
////Departure date
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
    public List<FlightDTO> findFlightsInTimeWindow(LocalDateTime depTime, LocalDateTime arrTime){
        log.trace("Searching for flights that occur in time window between");
        return flightRepository.findAllByDepartureTimeAfter(depTime).stream()
                .filter(flight -> flight.getArrivalTime().isBefore(arrTime))
                .map(flightMapper::fromEntityToDto)
                .toList();
    }


}