package cb13.project.service.impl;

import cb13.project.entities.Address;
import cb13.project.repository.AddressRepository;
import cb13.project.service.AddressService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }


    @Override
    public void deleteAddressById(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.getById(id);
    }
}
