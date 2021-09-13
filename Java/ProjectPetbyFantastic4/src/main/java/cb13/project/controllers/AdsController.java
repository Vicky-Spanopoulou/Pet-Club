/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.AdsDTO;
import cb13.project.entities.Address;
import cb13.project.entities.Ads;
import cb13.project.entities.City;
import cb13.project.entities.PetCategory;
import cb13.project.entities.User;
import cb13.project.service.AddressService;
import cb13.project.service.AdsService;
import cb13.project.service.CityService;
import cb13.project.service.PetCategoryService;
import cb13.project.service.UserService;

import java.security.Principal;
import java.util.Date;
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
@RequestMapping(path = "/ads")
public class AdsController {

    @Autowired
    AdsService adsService;

    @Autowired
    AddressService addressService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Autowired
    PetCategoryService petCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createAd(@Valid @RequestBody AdsDTO adsDTO , Principal principal) {
        User userPrincipal = userService.findUserByUsername(principal.getName());


        User user = userService.findUserById(adsDTO.getUser());
        City city = cityService.findCityByName(adsDTO.getCity());
        PetCategory petCategory = petCategoryService.findPetCategoryByName(adsDTO.getPetCategory());
        if (userPrincipal.getRole().equals("ROLE_ADMIN") || user.getUsername().equals(principal.getName())) {
            //create an address and save it
            Address address = new Address(
                    adsDTO.getStreet(),
                    adsDTO.getLongitude(),
                    adsDTO.getLatitude(),
                    adsDTO.getPostalCode()
            );
            city.addAddress(address);
            addressService.saveAddress(address);

            Date today = new Date();
            //create and save the ad
            Ads ad = new Ads(
                    adsDTO.getPetName(),
                    adsDTO.getChipNumber(),
                    adsDTO.getSex(),
                    adsDTO.getSomeWords(),
                    adsDTO.getDescriptions(),
                    today,
                    adsDTO.getImage(),
                    adsDTO.getAdCategory(),
                    adsDTO.getLostDate()
            );
            ad.setPetCategory(petCategory);
            ad.setAddress(address);
            ad.setUser(user);

            Ads savedAd = adsService.saveAds(ad);

            return new ResponseEntity<>(savedAd, OK);
        }
        return null;
    }
    
    @PostMapping("/edit")
    public ResponseEntity<?> editAd(@Valid @RequestBody AdsDTO adsDTO , Principal principal) {
        User userPrincipal = userService.findUserByUsername(principal.getName());
        Ads ad = adsService.findById(adsDTO.getId());
        User user = userService.findUserById(adsDTO.getUser());
        PetCategory petCategory = 
                petCategoryService.findPetCategoryByName(adsDTO.getPetCategory());
        Address address = user.getAddress();
        City city = cityService.findCityByName(adsDTO.getCity());
        if(userPrincipal.getRole().equals("ROLE_ADMIN") || user.getUsername().equals(principal.getName())){
            ad.setPetName(adsDTO.getPetName());
            ad.setChipNumber(adsDTO.getChipNumber());
            ad.setSex(adsDTO.getSex());
            ad.setAdCategory(adsDTO.getAdCategory());
            ad.setSomeWords(adsDTO.getSomeWords());
            ad.setDescriptions(adsDTO.getDescriptions());
            ad.setImage(adsDTO.getImage());
            ad.setUser(user);
            ad.setPetCategory(petCategory);
            ad.setLostDate(adsDTO.getLostDate());

            address.setStreet(adsDTO.getStreet());
            address.setLongitude(adsDTO.getLongitude());
            address.setLatitude(adsDTO.getLatitude());
            address.setPostalCode(adsDTO.getPostalCode());

            city.addAddress(address);
            addressService.saveAddress(address);

            adsService.saveAds(ad);

            return new ResponseEntity<>("DONE", OK);
        }

        return new ResponseEntity<>("POTE",OK);
    }
    
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAds(){
        List<Ads> ads = adsService.findAllAds();
        
        return new ResponseEntity<>(ads,OK);
    }  
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getAd(@PathVariable("id") Long adId){
        
        Ads ad = adsService.findById(adId);
        return new ResponseEntity<>(ad,OK);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable("id") Long adId, Principal principal){
        User userPrincipal = userService.findUserByUsername(principal.getName());
        Ads ad = adsService.findById(adId);
        if(userPrincipal.getRole().equals("ROLE_ADMIN") || ad.getUser().getId() == userPrincipal.getId()){
        adsService.deleteAdsById(adId);}
        return new ResponseEntity<>(null,OK);
    }
    
    
    
    

}
