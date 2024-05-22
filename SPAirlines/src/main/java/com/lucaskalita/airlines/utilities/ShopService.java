package com.lucaskalita.airlines.utilities;

import com.lucaskalita.airlines.globalExceptions.InsufficientFundsException;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.users.User;
import com.lucaskalita.airlines.users.UserMapper;
import com.lucaskalita.airlines.users.UserRepository;
import com.lucaskalita.airlines.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ShopService {
    private UserRepository userRepository;
    private UserService userService;
    private UserMapper userMapper;

    private boolean walletCheck(User user, BigDecimal money) {
        return user.getAccountBalance().compareTo(money) >= 0;
    }

    @Transactional
    public void buyTicket(TicketDTO ticketDTO, String username) {
        log.trace(" buying ticket for {}", ticketDTO.price());
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresentOrElse(
                user -> {
                    log.trace(" buying ticket for {}", ticketDTO.price());
                    subtractMoney(user, ticketDTO.price());
                },
                () -> {
                    throw new ObjectNotFoundException("Object by this parameter  (" + username + ") not found");
                });
    }

    private void subtractMoney(User user, BigDecimal money) {
        if (walletCheck(user, money)) {
            log.trace("Extracting money");
            user.setAccountBalance(user.getAccountBalance().subtract(money));
            userRepository.save(user);
        } else {
            throw new InsufficientFundsException
                    ("Not enough fund on the " + user.getUsername() + "'s account");
        }
    }
}