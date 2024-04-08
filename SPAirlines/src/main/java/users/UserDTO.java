package users;

import jakarta.persistence.EnumType;
import lombok.Builder;
import org.antlr.v4.runtime.misc.NotNull;
import ticket.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Builder
public record UserDTO(
        Long id,
        String username,
        String name,
        String surname,
        String Address,
        LocalDateTime dateOfBirth,
        String Pesel,
        String password,
        String email,
        BigDecimal accountBalance,
        List<Ticket> userListOfActiveTickets,
        List<Ticket> userListOfArchiveTickets,
        AccountType accountType
) {

}
