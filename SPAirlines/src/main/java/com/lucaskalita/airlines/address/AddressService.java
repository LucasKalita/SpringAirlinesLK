package com.lucaskalita.airlines.address;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressMapper addressMapper;

    public AddressDTO getAddressById(Long id){
        log.trace("Searching for address by id: {}", id);
        return addressMapper.fromEntityToDto(addressRepository.getReferenceById(id));
    }
    public List<AddressDTO> getAllAddressEntities(){
        log.trace("Searching for all Addresses");
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::fromEntityToDto).collect(Collectors.toList());
    }
    public List<AddressDTO> getAllAddressesByCountry(String country){
        log.trace("Filtering by country");
    }

}
