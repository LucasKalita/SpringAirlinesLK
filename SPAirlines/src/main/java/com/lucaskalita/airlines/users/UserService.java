package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.address.AddressMapper;
import com.lucaskalita.airlines.address.AddressRepository;
import com.lucaskalita.airlines.exceptions.NoMoneyOnTheAccountException;
import com.lucaskalita.airlines.exceptions.WrongUserIDException;
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
        }).orElseThrow(() -> new WrongUserIDException("No user with this id: " + id));
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

        User user = userRepository.findById(id).orElseThrow(() -> new WrongUserIDException("No user with this id: " + id));

        log.info("Found user with this id:{}, deleting now", id);
        userRepository.delete(user);
    }

    public void updateUser(Long id, UserDTO userDTO) {
        log.trace("Updating user with id: {}", id);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setUsername(userDTO.username());
            userToUpdate.setName(userDTO.name());
            userToUpdate.setSurname(userDTO.surname());
            userToUpdate.setAddress(addressMapper.fromDtoToEntity(userDTO.addressDTO()));
            userToUpdate.setDateOfBirth(userDTO.dateOfBirth());
            userToUpdate.setSocialSecurityNumber(userDTO.socialSecurityNumber());
            userToUpdate.setEmail(userDTO.email());
            userToUpdate.setAccountType(userDTO.accountType());

           userRepository.save(userToUpdate);

        } else {
            throw new WrongUserIDException("User with id: " + id + " not found");
        }
    }

    public void addMoneyToAccount(BigDecimal money, Long id) {
        log.trace("Adding money({}) to account for user with id: {}", money, userRepository.findById(id));
        Optional<User> userOptional = userRepository.findById(id);
        if (userRepository.existsById(id)) {
            User user = userOptional.get();
            BigDecimal newBalance = user.getAccountBalance().add(money);
            user.setAccountBalance(newBalance);
            userRepository.save(user);

            log.trace("New balance for the user with id {}: {}", user.getId(), newBalance);
        } else {
            throw new WrongUserIDException("User with id: " + id + " not found");
        }
    }

    public void removeMoneyFromAccount(BigDecimal money, User user) throws NoMoneyOnTheAccountException {
        log.trace("Removing money({}) from account for user: {}", money, user);

        BigDecimal currentBalance = user.getAccountBalance();

        if (currentBalance.compareTo(money) < 0) {
            throw new NoMoneyOnTheAccountException("Insufficient funds");
        } else {
            BigDecimal newBalance = currentBalance.subtract(money);
            user.setAccountBalance(newBalance);
            userRepository.save(user);
            log.trace("New balance for the user {}: {}", user.getId(), newBalance);
        }
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