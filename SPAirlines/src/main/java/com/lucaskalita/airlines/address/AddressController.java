package com.lucaskalita.airlines.address;

import com.lucaskalita.airlines.utilities.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private AddressService addressService;

    @PostMapping("/createAddress")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createAddress(AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }

    @DeleteMapping("/deleteAddress/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAddressById(@PathVariable Long id) {
        addressService.deleteAddressById(id);
    }
    @PutMapping("/updateAddress/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        addressService.updateAddress(id, addressDTO);
    }
    @GetMapping("/getAddressById/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AddressDTO findAddressById(@PathVariable Long id){
       return addressService.getAddressById(id);
    }
    @GetMapping("/getAddressByCountry/{country}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AddressDTO> findAddressByCountry(@PathVariable Country country){
        return addressService.getAllAddressesByCountry(country);
    }
    @GetMapping("/getAddressByCity/{city}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AddressDTO> findAddressByCity(@PathVariable String city){
        return addressService.getAllAddressesByCity(city);
    }
    @GetMapping("/getAddressByState/{state}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AddressDTO> findAddressByState(@PathVariable String state){
        return addressService.getAllAddressesByState(state);
    }
    @GetMapping("/getAddressByPostalCode/{postalCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<AddressDTO> findAddressById(@PathVariable String postalCode){
        return addressService.getAllAddressesByPostalCode(postalCode);
    }
}
