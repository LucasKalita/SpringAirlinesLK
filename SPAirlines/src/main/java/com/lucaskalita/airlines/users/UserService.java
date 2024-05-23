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
        userRepository.findById(id)
                .ifPresentOrElse(user -> {
                            log.trace("Updating User's details");
                            user.setUsername(userDTO.username());
                            user.setName(userDTO.name());
                            user.setSurname(userDTO.surname());
                            user.setDateOfBirth(userDTO.dateOfBirth());
                            user.setSocialSecurityNumber(userDTO.socialSecurityNumber());
                            user.setEmail(userDTO.email());
                            user.setAccountType(userDTO.accountType());
                            userRepository.save(user);
                        },
                        () -> {
                            throw new WrongObjectIdException("User with id: " + id + " not found");
                        });
    }

    public void updateUserAddress(Long id, UserDTO userDTO) {
        userRepository.findById(id)
                .ifPresentOrElse(user -> {
                    log.trace("Updating User's address ");
                    user.setAddress(addressMapper.fromDtoToEntity(userDTO.addressDTO()));
                    userRepository.save(user);
                },
                () -> {
                    throw new WrongObjectIdException("User with id: " + id + " not found");
                });
    }

    public void addMoneyToAccount(BigDecimal money, String username) {
        log.trace("Adding money({}) to account for user: {}", money, username);
        Optional<User> userOptional = userRepository.findByUsername(username);

    }

    public boolean walletCheck(User user, BigDecimal money) {
        return user.getAccountBalance().compareTo(money) >= 0;
    }

    public void subtractMoney(User user, BigDecimal money) {
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

    public List<UserDTO> findUsersBornBeforeCertainDate(LocalDate localDate) {
        log.trace("Searching for users born before {}", localDate);
        return userRepository.findAllByDateOfBirthBefore(localDate).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornAfterCertainDate(LocalDate localDate) {
        log.trace("Searching for users born after {}", localDate);
        return userRepository.findAllByDateOfBirthAfter(localDate).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUsersBornBetweenDates(LocalDate localDate, LocalDate localDate2) {
        log.trace("Searching for users born between dates: {} and {}", localDate, localDate2);
        return userRepository.findAllByDateOfBirthBetween(localDate, localDate2).stream()
                .map(userMapper::fromEntityToDto).toList();
    }

    public List<UserDTO> findUserByAccountType(AccountType accountType) {
        log.trace("Searching for {}", accountType);
        return userRepository.findAllByAccountType(accountType).stream()
                .map(userMapper::fromEntityToDto).toList();
    }
}