package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.utilities.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserDTO> {
    @Override
    public UserDTO fromEntityToDto(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .name(entity.getName())
                .surname(entity.getSurname())
                .address(entity.getAddress())
                .dateOfBirth(entity.getDateOfBirth())
                .socialSecurityNumber(entity.getSocialSecurityNumber())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .accountBalance(entity.getAccountBalance())
                .userListOfActiveTicketsIds(entity.getUserListOfActiveTicketsIds())
                .userListOfArchiveTicketsIds(entity.getUserListOfArchiveTicketsIds())
                .accountType(entity.getAccountType())
                .build();
    }

    @Override
    public User fromDtoToEntity(UserDTO dto) {
        return User.builder()
                .id(dto.id())
                .username(dto.username())
                .name(dto.name())
                .surname(dto.surname())
                .address(dto.address())
                .dateOfBirth(dto.dateOfBirth())
                .email(dto.email())
                .socialSecurityNumber(dto.socialSecurityNumber())
                .password(dto.password())
                .accountBalance(dto.accountBalance())
                .userListOfActiveTicketsIds(dto.userListOfActiveTicketsIds())
                .userListOfArchiveTicketsIds(dto.userListOfArchiveTicketsIds())
                .accountType(dto.accountType())
                .build();
    }
}
