/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.BusinessDTO;
import cb13.project.entities.Address;
import cb13.project.entities.Business;
import cb13.project.entities.BusinessCategory;
import cb13.project.entities.City;
import cb13.project.entities.User;
import cb13.project.enumeration.PageStatus;
import cb13.project.service.AddressService;
import cb13.project.service.BusinessCategoryService;
import cb13.project.service.BusinessService;
import cb13.project.service.CityService;
import cb13.project.service.UserService;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vicky
 */

@RestController
@RequestMapping(path = "/business")
public class BusinessController {
    
    @Autowired
    BusinessService businessService;
    
    @Autowired
    BusinessCategoryService businessCategoryService;
    
    @Autowired
    AddressService addressService;

    @Autowired
    CityService cityService;
    
    @Autowired 
    UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createBusiness(@Valid @RequestBody BusinessDTO businessDTO ) {

        City city = cityService.findCityByName(businessDTO.getCity());
        BusinessCategory businessCategory = businessCategoryService.findByName(businessDTO.getBusinessCategory());
        
        //create an address and save it
        Address address = new Address(
                businessDTO.getStreet(),
                businessDTO.getLongitude(),
                businessDTO.getLatitude(),
                businessDTO.getPostalCode()
        );
        city.addAddress(address);
        addressService.saveAddress(address);

        //create and save the business page
        Business business = new Business(
                businessDTO.getName(),
                businessDTO.getEmail(),
                businessDTO.getPhone(),
                businessDTO.getDescription(),
                businessDTO.getText(),
                businessDTO.getImgLogo(),
                businessDTO.getImgBackground(),
                businessDTO.getFacebook(),
                businessDTO.getInstagram(),
                businessDTO.getPageUrl(),
                PageStatus.UNCHECKED.toString()
        );
        business.setAddress(address);
        business.setBusinessCategory(businessCategory);
        
        businessService.saveBusiness(business);
        
        return new ResponseEntity<>(null, OK);
    }
    
    
    @PostMapping("/edit")
    public ResponseEntity<?> editBusiness(@Valid @RequestBody BusinessDTO businessDTO , Principal principal) {

        User userPrincipal = userService.findUserByUsername(principal.getName());

        Business business = businessService.findById(businessDTO.getId());

        if(userPrincipal.getRole().equals("ROLE_ADMIN") || userPrincipal.getBusiness().getId() == business.getId()){

        BusinessCategory businessCategory = businessCategoryService.findByName(businessDTO.getBusinessCategory());
        Address address = business.getAddress();
        City city = cityService.findCityByName(businessDTO.getCity());
        
        business.setName(businessDTO.getName());
        business.setEmail(businessDTO.getEmail());
        business.setPhone(businessDTO.getPhone());
        business.setDescription(businessDTO.getDescription());
        business.setText(businessDTO.getText());
        business.setImgLogo(businessDTO.getImgLogo());
        business.setImgBackground(businessDTO.getImgBackground());
        business.setFacebook(businessDTO.getFacebook());
        business.setInstagram(businessDTO.getInstagram());
        business.setPageUrl(businessDTO.getPageUrl());
        if(business.getStatus() != null){
            business.setStatus(businessDTO.getStatus());
        }
        
        
        address.setStreet(businessDTO.getStreet());
        address.setLongitude(businessDTO.getLongitude());
        address.setLatitude(businessDTO.getLatitude());
        address.setPostalCode(businessDTO.getPostalCode());
        
        city.addAddress(address);
        addressService.saveAddress(address);
        business.setAddress(address);
        business.setBusinessCategory(businessCategory);
        businessService.saveBusiness(business);

        return new ResponseEntity<>(null, OK);}
        return new ResponseEntity<>("problem",OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllBusinesses(Principal principal){
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getRole().equals("ROLE_ADMIN")){
        List<Business> business = businessService.findAllBusiness();
        return new ResponseEntity<>(business,OK);}
        return null;
    }  
    
    @GetMapping("/getAllPublished")
    public ResponseEntity<?> getAllPublishedBusinesses(){
        List<Business> businesses = businessService.findAllBusinessesByStatus(PageStatus.PUBLISHED.toString());

        return new ResponseEntity<>(businesses,OK);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBusinessById(@PathVariable("id") Long businessId){
        
        Business business = businessService.findById(businessId);
        return new ResponseEntity<>(business,OK);
    }
    
    @GetMapping("/getbyUser/{id}")
    public ResponseEntity<?> getBusinessByUser(@PathVariable("id") Long userId){
        User user= userService.findUserById(userId);
        Business business = user.getBusiness();
        return new ResponseEntity<>(business,OK);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable("id") Long businessId , Principal principal){
        User userPrincipal = userService.findUserByUsername(principal.getName());
        Business bus = businessService.findById(businessId);
        if(userPrincipal.getRole().equals("ROLE_ADMIN") || userPrincipal.getBusiness().getId() == bus.getId()){
            businessService.deleteBusinessById(businessId);
        }

        return new ResponseEntity<>(null,OK);
    }


    @GetMapping("/unchekedTotalNumber")
    public ResponseEntity<?> findTotalNumberOfUncheckedBusiness(Principal principal) {
         User userPrincipal =userService.findUserByUsername(principal.getName());
         if(userPrincipal.getRole().equals("ROLE_ADMIN")){
        return new ResponseEntity<>(businessService.findTotalNumberOfUnchecked(),OK);}
         return null;
    }

    @GetMapping("/avgRating")
    public ResponseEntity<?> findAllBusinessAvgRating() {
        return new ResponseEntity<>(businessService.findAllBusinessAvgRating(),OK);
    }
    
}
