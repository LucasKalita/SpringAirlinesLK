package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.address.AddressMapper;
import com.lucaskalita.airlines.address.AddressRepository;
import com.lucaskalita.airlines.globalExceptions.InsufficientFundsException;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public UserDTO findUserByID(Long id) {
        log.info("Searching for user with id: {}", id);
        return userRepository.findById(id).map(user -> {
            log.info("Found user with this id:{}", id);
            return userMapper.fromEntityToDto(user);
        }).orElseThrow(() -> new WrongObjectIdException("No user with this id: " + id));
    }

    public Long createUser(UserDTO userDTO) {
        log.trace("creating new user");
        User user = userMapper.fromDtoToEntity(userDTO);
        Address address = user.getAddress();
        addressRepository.findByComparedHash(address.getComparedHash()).ifPresent(user::setAddress);
        user.setAccountBalance(BigDecimal.valueOf(0));
        User saved = userRepository.save(user);

        return saved.getId();
    }

    public void deleteUserByID(Long id) {
        log.info("Deleting user by id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new WrongObjectIdException("No user with this id: " + id));
        log.info("Found user with this id:{}, deleting now", id);
        userRepository.delete(user);
    }

    public void updateUserDetails(Long id, UserDTO userDTO) {
        log.trace("Updating user with id: {}", id);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            log.trace("Updating User's details");
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(userDTO.username());
            userToUpdate.setName(userDTO.name());
            userToUpdate.setSurname(userDTO.surname());
            userToUpdate.setDateOfBirth(userDTO.dateOfBirth());
            userToUpdate.setSocialSecurityNumber(userDTO.socialSecurityNumber());
            userToUpdate.setEmail(userDTO.email());
            userToUpdate.setAccountType(userDTO.accountType());
            userRepository.save(userToUpdate);
        } else {
            throw new WrongObjectIdException("User with id: " + id + " not found");
        }
    }
    public void updateUserAddress(Long id, UserDTO userDTO){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            log.trace("Updating User's address ");
            User userToUpdate = userOptional.get();
            userToUpdate.setAddress(addressMapper.fromDtoToEntity(userDTO.addressDTO()));
            userRepository.save(userToUpdate);
        } else {
            throw new WrongObjectIdException("User with id: " + id + " not found");
        }
    }

    public void addMoneyToAccount(BigDecimal money, String username) {
        log.trace("Adding money({}) to account for user: {}", money, username);
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new ObjectNotFoundException("No object by this parameter: " + username);
        } else {
            log.trace("Adding {} to {} wallet", money, username);
            user.setAccountBalance(user.getAccountBalance().add(money));
            userRepository.save(user);
        }
    }

    private boolean walletCheck(String username, BigDecimal money) {
        User user = userRepository.findByUsername(username);
        return user.getAccountBalance().compareTo(money) >= 0;
    }

    public void withdrawMoneyFromAccount(BigDecimal money, String username) {
        log.trace("Removing money ({}) from account of user: {}", money, username);
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new ObjectNotFoundException("No object by this parameter: " + username);
        } else {
            if (walletCheck(username, money)) {
                log.trace("Adding {} to {} wallet", money, username);
                user.setAccountBalance(user.getAccountBalance().add(money));
                userRepository.save(user);
            } else throw new InsufficientFundsException("Not enough funds on account");
        }
    }
    public void buyTicket(){
        log.trace("Buying ticket");

    }

    public List<UserDTO> findUsersBornBeforeCertainDate(LocalDate localDate) {
        log.trace("Searching for users born before {}", localDate);
        return userRepository.findAllByDateOfBirthBefore(localDate).stream().map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornAfterCertainDate(LocalDate localDate) {
        log.trace("Searching for users born after {}", localDate);
        return userRepository.findAllByDateOfBirthAfter(localDate).stream().map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornBetweenDates(LocalDate localDate, LocalDate localDate2) {
        log.trace("Searching for users born between dates: {} and {}", localDate, localDate2);
        return userRepository.findAllByDateOfBirthBetween(localDate, localDate2).stream().map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUserByAccountType(AccountType accountType) {
        log.trace("Searching for {}", accountType);
        return userRepository.findAllByAccountType(accountType).stream().map(userMapper::fromEntityToDto).toList();
    }


}