package users;

import exceptions.WrongTicketIDException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticket.Ticket;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> findAllUsers(){
        log.trace("Searching all users");
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(),
                        user.getUsername(),
                        user.getName(),
                        user.getSurname(),
                        user.getPesel(),
                        user.getDateOfBirth(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getAccountBalance(),
                        user.getAccountType(),
                        user.getUserListOfActiveTickets(),
                        user.getUserListOfArchiveTickets(),
                        user.getId()));
    }
    public User findUserByID(Long id) {
        log.info("Searching for ticket with id: {}", id);
        return userRepository.findById(id).map(user -> {
            log.info("Found user with this id:{}", id);
            return user;
        }).orElseThrow(() -> new WrongTicketIDException("No ticket with this id: " + id));
    }
    @Transactional
    public void deleteUserByID(Long id){
        log.info("Deleting user by id {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new WrongTicketIDException("No user with this id: " + id));

        log.info("Found user with this id:{}, deleting now", id);
        userRepository.delete(user);
    }

    @Transactional
    public void addTicketToAccount(Ticket ticket, Long id){
        log.trace("{} is buying a ticket ({})",id.getClass(),ticket);
        findUserByID(id).getUserListOfActiveTickets().add(ticket);
    }
    @Transactional
    public void RemoveTicketFromAccount(Ticket ticket, Long id){
        log.trace("{} is removing a ticket ({})",id.getClass(),ticket);
        findUserByID(id).getUserListOfActiveTickets().remove(ticket);

    }
    public void AddMoneyToAccount(BigDecimal money, User user){
        log.trace("Adding money({}) to account", money);
        user.setAccountBalance(user.getAccountBalance().add(money));
        log.trace("New balance for the {} account: {}", user, user.getAccountBalance());
    }


}
