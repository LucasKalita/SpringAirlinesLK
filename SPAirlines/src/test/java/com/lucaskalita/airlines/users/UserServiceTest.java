package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.Main;
import com.lucaskalita.airlines.address.Address;
import com.lucaskalita.airlines.ticket.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@Slf4j
@SpringBootTest(classes = Main.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void testFindUserById_WhenUserExists() {
        // Given
        Long userId = 35L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        User foundUser = userService.findUserByID(userId);

        // Then
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
    }

    @Test
    void testFindUserById_WhenUserDoesNotExist() {
        // Given
        Long userId = 35L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(WrongUserIDException.class, () -> {
            userService.findUserByID(userId);
        });
    }
    @Test
    void testDeleteUserByID() {
       //Given
        User userToDelete = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(userToDelete));

        //when
        userService.deleteUserByID(1L);

       //then
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(userToDelete);

    }

    @Test
    void testDeleteUserByID_UserNotFound() {

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(WrongUserIDException.class, () -> userService.deleteUserByID(1L));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateUser() {

        Address address1 = new Address();
        LocalDate date1 = LocalDate.of(1990, 1, 18);
        Set<Ticket> tickets = new HashSet<Ticket>();
        Set<Ticket> ticketsPast = new HashSet<Ticket>();

        User userTest = new User();
        userTest.setId(144L);
        userTest.setUsername("john_doe");
        userTest.setName("John");
        userTest.setSurname("Doe");
        userTest.setAddress(address1);
        userTest.setDateOfBirth(date1);
        userTest.setSocialSecurityNumber("123-45-6789");
        userTest.setPassword("password123");
        userTest.setEmail("john.doe@example.com");
        userTest.setAccountBalance(BigDecimal.valueOf(1000));
        userTest.setAccountType(AccountType.REGISTERED);
        userTest.setUserListOfActiveTicketsIds(tickets);
        userTest.setUserListOfArchiveTicketsIds(ticketsPast);

        UserDTO userDTOTest = new UserDTO(144L,
                "john_doe",
                "John",
                "Doe",
                address1,
                date1,
                "123-45-6789",
                "password123",
                "john.doe@example.com",
                BigDecimal.valueOf(1000),
                tickets,
                ticketsPast,
               AccountType.REGISTERED);


            when(userRepository.findById(144L)).thenReturn(Optional.of(userTest));

            when(passwordEncoder.encode(any())).thenReturn("");
        when(userMapper.fromEntityToDto(any())).thenReturn(userDTOTest);
        UserDTO updatedUserDTO = userService.updateUser(144L, userDTOTest);
        assertEquals(userDTOTest, updatedUserDTO);
        }

}