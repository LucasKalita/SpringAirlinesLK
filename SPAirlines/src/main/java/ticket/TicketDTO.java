package ticket;

import flight.Flight;
import seatReservation.Seat;
import users.User;

public record TicketDTO(
        Long id,
        String seatNumber,
        Seat seat,
        Flight flight,
        User user
) {
}
