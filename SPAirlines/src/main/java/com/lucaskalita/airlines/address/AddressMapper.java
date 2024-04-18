package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.utilities.Mapper;

public class AddressMapper implements Mapper<Address, AddressDTO> {
    @Override
    public AddressDTO fromEntityToDto(Address entity) {
        return AddressDTO.builder()
                .id(entity.getId())
                .country(entity.getCountry())
                .state(entity.getState())
                .city(entity.getCity())
                .postalCode(entity.getPostalCode())
                .street(entity.getStreet())
                .parcelNumber(entity.getParcelNumber())
                .build();
    }

    @Override
    public Address fromDtoToEntity(AddressDTO dto) {
        return Address.builder()
                .id(dto.id())
                .country(dto.country())
                .state(dto.state())
                .city(dto.city())
                .postalCode(dto.postalCode())
                .street(dto.street())
                .parcelNumber(dto.parcelNumber())
                .build();
    }
}
