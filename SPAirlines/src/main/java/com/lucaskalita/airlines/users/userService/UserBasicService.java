package com.lucaskalita.airlines.users.userService;
import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.address.AddressMapper;
import com.lucaskalita.airlines.address.AddressRepository;
import com.lucaskalita.airlines.globalExceptions.ObjectNotFoundException;
import com.lucaskalita.airlines.globalExceptions.WrongObjectIdException;
import com.lucaskalita.airlines.users.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserBasicService {

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
    public UserDTO findByUsername(String username){
        log.info("Searching for user by :" + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new ObjectNotFoundException("No object by this parameter: " + username));
        return userMapper.fromEntityToDto(user);
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

}