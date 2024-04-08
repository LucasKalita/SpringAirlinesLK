package seatReservation;

import exceptions.WrongSeatIDException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SeatService {
    @Autowired
    SeatRepository seatRepository;

    public Seat getSeatByID(Long id){
        log.trace("Searching for seat by id:{}", id);
        return seatRepository.findById(id)
                .map(seat ->{log.trace("Found id:{}", id);
                 return seat;
                }).orElseThrow(WrongSeatIDException::new);

    }
    public List<Seat> findAllSeats(){
        log.trace("Searching for all seats");
        return seatRepository.findAll();
    }
}
