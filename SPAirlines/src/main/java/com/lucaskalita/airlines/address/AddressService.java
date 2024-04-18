package com.lucaskalita.airlines.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressMapper addressMapper;

    public AddressDTO getAddressById(Long id){
        log.trace("Searching for address by id: {}", id);
        return addressRepository.getById(id).
    }
}
