package com.lucaskalita.airlines.seatReservation;

import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SeatService {

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;

    public SeatDTO findSeatById(Long id) {
        log.trace("Searching for seat by id: {}", id);
        return seatRepository.findById(id)
                .map(seatMapper::fromEntityToDto)
                .orElseThrow(()->new WrongObjectIdException("No seat with this id: "+ id));
    }
    public Long createSeat(SeatDTO seatDTO){
        log.trace("Creating seat");
        Seat seat = seatMapper.fromDtoToEntity(seatDTO);
        seatRepository.save(seat);
        return seat.getId();
    }
    public void deleteSeat(Long id) {
        log.trace("Deleting seat");
        Optional<Seat> seat = seatRepository.findById(id);

        seat.ifPresentOrElse(
                x -> {
                    log.trace("Seat found, deleting");
                    seatRepository.deleteById(id);
                },
                () -> {
                    throw new WrongObjectIdException("No object with this id: " + id);
                }
        );
    }
    public List<SeatDTO> findAllUnReservedSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsReservedFalse()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }
    public  List<SeatDTO> findAllReservedSeats(){
        log.trace("Searching for all reserved seats");
        return seatRepository.findAllByIsReservedTrue()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }

    public List<SeatDTO> findAllRegularSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsPremiumFalse()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }

    public List<SeatDTO> findAllPremiumSeats() {
        log.trace("Searching for all available seats");
        return seatRepository.findAllByIsPremiumTrue()
                .stream()
                .map(seatMapper::fromEntityToDto)
                .toList();
    }
}