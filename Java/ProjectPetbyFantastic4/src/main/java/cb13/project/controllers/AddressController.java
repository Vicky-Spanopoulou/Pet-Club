package cb13.project.controllers;

import cb13.project.dto.AddressDTO;
import cb13.project.entities.Address;
import cb13.project.entities.City;
import cb13.project.service.AddressService;
import cb13.project.service.CityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vicky
 */
@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping(path = "/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<?> createAddress(@Valid @RequestBody AddressDTO addressDTO ) {
        City city = cityService.findCityByName(addressDTO.getCity());
        Address address = new Address(
            addressDTO.getStreet(),
            addressDTO.getLongitude(),
            addressDTO.getLatitude(),
            addressDTO.getPostalCode()
        );
        city.addAddress(address);
        addressService.saveAddress(address);
        return new ResponseEntity<>(null, OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editAddress(@Valid @RequestBody AddressDTO addressDTO) {

        Address address = addressService.findById(addressDTO.getId());
        City city = cityService.findCityByName(addressDTO.getCity());

        address.setStreet(addressDTO.getStreet());
        address.setLongitude(addressDTO.getLongitude());
        address.setLatitude(addressDTO.getLatitude());
        address.setPostalCode(addressDTO.getPostalCode());      
   
        city.addAddress(address);
        addressService.saveAddress(address);

        return new ResponseEntity<>(null, OK);
    }
    
    
    
    
}
