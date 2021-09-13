package cb13.project.service;

import cb13.project.entities.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(Address address);



    void deleteAddressById(Long addressId);

    List<Address> findAllAddress();
    
    Address findById(Long id);
    
}
