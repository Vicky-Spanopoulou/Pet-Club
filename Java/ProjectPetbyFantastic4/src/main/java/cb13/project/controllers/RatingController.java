/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.RatingDTO;
import cb13.project.entities.Business;
import cb13.project.entities.Rating;
import cb13.project.entities.User;
import cb13.project.service.BusinessService;
import cb13.project.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cb13.project.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

/**
 *
 * @author vicky
 */

@RestController
@RequestMapping(path = "/rating")
public class RatingController {
    
    @Autowired
    RatingService ratingService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    BusinessService businessService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createRating(@Valid @RequestBody RatingDTO ratingDTO , Principal principal) {

        User user = userService.findUserById(ratingDTO.getUserId());
        if(!user.getUsername().equals(principal.getName())){return null;}
        Business business = businessService.findById(ratingDTO.getBusinessId());

        //create and save the rating
        Rating rating = new Rating(
                ratingDTO.getRatingNumber()
        );
        rating.setUser(user);
        rating.setBusiness(business);
        Rating createdRating = ratingService.saveRating(rating);
        
        return new ResponseEntity<>(createdRating, OK);
    }
    
    @GetMapping("/get/{userId}/{businessId}")
    public ResponseEntity<?> getRating(@PathVariable("userId") Long userId,@PathVariable("businessId") Long businessId ){
        
        User user = userService.findUserById(userId);



        Business business = businessService.findById(businessId);
        Rating rating = ratingService.findByUserAndBusiness(user, business);
        if(rating == null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(rating,OK);
    }
    
    @PostMapping("/edit")
    public ResponseEntity<?> editRating(@Valid @RequestBody RatingDTO ratingDTO,Principal principal) {
        
        Rating rating = ratingService.findById(ratingDTO.getId());
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId() != rating.getUser().getId()){return null;}
        rating.setRatingNumber(ratingDTO.getRatingNumber());
        ratingService.saveRating(rating);
        
        return new ResponseEntity<>(null, OK);
    }
    
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable("id") Long id,Principal principal){
        Rating rat = ratingService.findById(id);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId() != rat.getUser().getId()){return null;}
        ratingService.deleteRatingById(id);
        return new ResponseEntity<>(null,OK);
    }


    @GetMapping("/getBusinessRating/{id}")
    public ResponseEntity<?> getBusinessRating(@PathVariable("id") Long id){
        return new ResponseEntity<>(ratingService.findBusinessAvgRating(id),OK);
    }
    
    
    
}
