package com.lucaskalita.airlines.users.userService;

import com.lucaskalita.airlines.globalExceptions.InsufficientFundsException;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.ticket.TicketDTO;
import com.lucaskalita.airlines.ticket.TicketMapper;
import com.lucaskalita.airlines.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TicketMapper ticketMapper;

    public void addMoneyToAccount(BigDecimal money, String username) {
        log.trace("Adding money({}) to account for user: {}", money, username);
        userRepository.findByUsername(username).ifPresentOrElse(
                user -> {
                    user.setAccountBalance(user.getAccountBalance().add(money));
                },
                () -> {
                    throw new ObjectNotFoundException("No object found");
                });

    }

    private boolean walletCheck(User user, BigDecimal money) {
        return user.getAccountBalance().compareTo(money) >= 0;
    }

    private void subtractMoney(User user, BigDecimal money) {
        if (walletCheck(user, money)) {
            log.trace("Extracting money");
            user.setAccountBalance(user.getAccountBalance().subtract(money));
            userRepository.save(user);
        } else {
            throw new InsufficientFundsException("Not enough fund on the " + user.getUsername() + "'s account");
        }
    }

    public void withdrawMoneyFromAccount(BigDecimal money, String username) {
        log.trace("Removing money ({}) from account of user: {}", money, username);
        userRepository.findByUsername(username)
                .ifPresentOrElse(user -> {
                            subtractMoney(user, money);
                        },
                        () -> {
                            throw new ObjectNotFoundException("No object by this parameter: " + username);
                        });
    }

    public List<UserDTO> findUserByAccountType(AccountType accountType) {
        log.trace("Searching for {}", accountType);
        return userRepository.findAllByAccountType(accountType).stream()
                .map(userMapper::fromEntityToDto).toList();
    }
    public UserDTO findUserByEmail(String email) {
        log.trace("Searching for user by email: " + email);
        return userRepository.findByEmail(email)
                .map(user -> {
                    log.info("Found user with this email: {}", email);
                    return userMapper.fromEntityToDto(user);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
    public UserDTO findUserBySecurityNumber(String socialSecurityNumber) {
        log.trace("Searching for user by socialSecurityNumber: " + socialSecurityNumber);
        return userRepository.findBySocialSecurityNumber(socialSecurityNumber)
                .map(user -> {
                    log.info("Found user with this socialSecurityNumber: {}", socialSecurityNumber);
                    return userMapper.fromEntityToDto(user);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
    public List<TicketDTO> getUserTicketList(String username){
        log.trace("Getting " + username + " list of active tickets");
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("No object by this parameter: " + username));

        return user.getActiveTickets().stream().map(ticketMapper::fromEntityToDto).toList();

    }

}