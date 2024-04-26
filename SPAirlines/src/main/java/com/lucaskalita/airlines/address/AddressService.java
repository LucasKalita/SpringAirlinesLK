package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.exceptions.WrongAddressIdException;
import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddressService {

   private final AddressRepository addressRepository;

   private final AddressMapper addressMapper;

    public AddressDTO getAddressById(Long id) {
        log.trace("Searching for address by id: {}", id);
        if ((addressRepository.findById(id).isPresent())) {
            return addressMapper.fromEntityToDto(addressRepository.getReferenceById(id));
        } else {
            throw new WrongAddressIdException("Wrong address" );
        }
    }

    public void deleteAddressById(Long id) {
        log.trace("Searching for address by id: {}", id);
        if ((addressRepository.findById(id).isPresent())) {
            log.trace("Address found, deleting");
            addressRepository.deleteById(id);
        } else {
            throw new WrongAddressIdException("Wrong address");
        }
    }

    public AddressDTO addAddress(AddressDTO addressDTO) {
        log.trace("Adding new address");
        return addressMapper.fromEntityToDto(addressRepository.save(addressMapper.fromDtoToEntity(addressDTO)));
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        log.trace("Updating address with id: {}", id);

        Optional<Address> addressOptional = addressRepository.findById(id);

        if (addressOptional.isPresent()) {
            Address addressToUpdate = addressOptional.get();

            addressToUpdate.setCountry(addressDTO.country());
            addressToUpdate.setState(addressDTO.state());
            addressToUpdate.setCity(addressDTO.city());
            addressToUpdate.setStreet(addressDTO.street());
            addressToUpdate.setPostalCode(addressDTO.postalCode());
            addressToUpdate.setParcelNumber(addressDTO.parcelNumber());

            return addressMapper.fromEntityToDto(addressRepository.save(addressToUpdate));
        } else {
            log.warn("Address with id {} not found", id);
            throw new WrongAddressIdException("Wrong  Address" );
        }
    }

    public List<AddressDTO> getAllAddressesByCountry(Country country) {
        log.trace("Filtering by country ({})", country);
        return addressRepository.findAllAddressByCountry(country)
                .stream()
                .map(addressMapper::fromEntityToDto)
                .toList();
    }
    public List<AddressDTO> getAllAddressesByCity(String city) {
        log.trace("Filtering by city ({})", city);
        return addressRepository.findAllAddressByCity(city)
                .stream()
                .map(addressMapper::fromEntityToDto)
                .toList();
    }
    public List<AddressDTO> getAllAddressesByPostalCode(String postalCode) {
        log.trace("Filtering by postal code ({})", postalCode);
        return addressRepository.findAllAddressByPostalCode(postalCode)
                .stream()
                .map(addressMapper::fromEntityToDto)
                .toList();
    }
    public List<AddressDTO> getAllAddressesByState(String state) {
        log.trace("Filtering by state ({})", state);
        return addressRepository.findAllAddressByState(state)
                .stream()
                .map(addressMapper::fromEntityToDto)
                .toList();
    }

}
