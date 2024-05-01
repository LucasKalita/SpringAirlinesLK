package com.lucaskalita.airlines.users;

import com.lucaskalita.airlines.address.AddressMapper;
import com.lucaskalita.airlines.utilities.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class UserMapper implements Mapper<User, UserDTO> {


    private final AddressMapper addressMapper;
    @Override
    public UserDTO fromEntityToDto(User entity) {
        return UserDTO.builder()
                .username(entity.getUsername())
                .name(entity.getName())
                .surname(entity.getSurname())
                .addressDTO(addressMapper.fromEntityToDto(entity.getAddress()))
                .dateOfBirth(entity.getDateOfBirth())
                .socialSecurityNumber(entity.getSocialSecurityNumber())
                .email(entity.getEmail())
                .accountType(entity.getAccountType())
                .build();
    }

    @Override
    public User fromDtoToEntity(UserDTO dto) {
        return User.builder()
                .username(dto.username())
                .name(dto.name())
                .surname(dto.surname())
                .address(addressMapper.fromDtoToEntity(dto.addressDTO()))
                .dateOfBirth(dto.dateOfBirth())
                .email(dto.email())
                .socialSecurityNumber(dto.socialSecurityNumber())
                .accountType(dto.accountType())
                .build();
    }
}
