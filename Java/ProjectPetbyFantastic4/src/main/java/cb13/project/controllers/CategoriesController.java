package cb13.project.controllers;


import cb13.project.entities.BusinessCategory;
import cb13.project.entities.City;
import cb13.project.entities.PetCategory;
import cb13.project.entities.User;
import cb13.project.enumeration.AdCategory;
import cb13.project.enumeration.ExaminationCategory;
import cb13.project.enumeration.PageStatus;
import cb13.project.enumeration.PetSex;
import cb13.project.enumeration.Role;
import cb13.project.enumeration.TransactionCategory;
import cb13.project.service.BusinessCategoryService;
import cb13.project.service.CityService;
import cb13.project.service.PetCategoryService;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

import cb13.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tsepe
 */
@RestController
@RequestMapping(path = "/categories")
public class CategoriesController {
    
    @Autowired
    CityService cityService;
    
    @Autowired
    PetCategoryService petCatService;
    
    @Autowired
    BusinessCategoryService businessCategoryService;

    @Autowired
    UserService userService;

    @PostMapping("/city/create")
    public ResponseEntity<?> createCity(@Valid @RequestBody City city , Principal principal){


        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}

        cityService.saveCity(city);
        return new ResponseEntity<>(null,OK);
    }
    
    @PostMapping("/city/edit")
    public ResponseEntity<?> editCity(@Valid @RequestBody City city ,Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}

        cityService.saveCity(city);
        
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/city/getCityById/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") Long id){
        City city = cityService.findCityById(id);
        
        return new ResponseEntity<>(city,OK);
    }
    
    @GetMapping("/city/getCityByName/{name}")
    public ResponseEntity<?> getCityByName(@PathVariable("name") String name){
        City city = cityService.findCityByName(name);
        
        return new ResponseEntity<>(city,OK);
    }
    
    @GetMapping("/city/getAll")
    public ResponseEntity<?> getAllCities(){
        List<City> cities = cityService.findAllCities();
        
        return new ResponseEntity<>(cities,OK);
    }
    
    @GetMapping("/city/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable("id") Long id,Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (userPrincipal.getRole().equals("ROLE_ADMIN")){ cityService.deleteCityById(id);}


        
        return new ResponseEntity<>(null,OK);
    }
    
    
    @PostMapping("/pet_category/create")
    public ResponseEntity<?> createPetCat(@Valid @RequestBody PetCategory petCat ,Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}
        petCatService.savePetCategory(petCat);
        
        return new ResponseEntity<>(null,OK);
    }
    
    @PostMapping("/pet_category/edit")
    public ResponseEntity<?> editPetCat(@Valid @RequestBody PetCategory petCat , Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}
        petCatService.savePetCategory(petCat);
        
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/pet_category/getPetCat/{id}")
    public ResponseEntity<?> getPetCat(@PathVariable("id") Long id){
        PetCategory petCat = petCatService.findPetCategoryById(id);
        
        return new ResponseEntity<>(petCat,OK);
    }
    
    @GetMapping("/pet_category/getAll")
    public ResponseEntity<?> getAllPetCats(){
        List<PetCategory> petCategories = petCatService.findAllPetCategories();
        
        return new ResponseEntity<>(petCategories,OK);
    }
    
    @GetMapping("/pet_category/delete/{id}")
    public ResponseEntity<?> deletePetCat(@PathVariable("id") Long id ,Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (userPrincipal.getRole().equals("ROLE_ADMIN")){ petCatService.deletePetCategoryById(id);}

        
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/pet_sex/getAll")
    public ResponseEntity<?> getAllPetSexies(){
        PetSex[] sex = PetSex.getAll();
        
        return new ResponseEntity<>(sex,OK);
    }
    
    
    @GetMapping("/exam_cat/getAll")
    public ResponseEntity<?> getAllExamCat(){
        ExaminationCategory[] categories = ExaminationCategory.getAll();
        
        return new ResponseEntity<>(categories,OK);
    }
    
    @GetMapping("/roles/getAll")
    public ResponseEntity<?> getAllRoles(){
        Role[] roles = Role.getAllRoles();
        
        return new ResponseEntity<>(roles,OK);
    }
    
    @GetMapping("/adCategories/getAll")
    public ResponseEntity<?> getAllAdCat(){
        AdCategory[] adCats = AdCategory.getAll();
        
        return new ResponseEntity<>(adCats,OK);
    }
    
    @GetMapping("/transactionCat/getAll")
    public ResponseEntity<?> getAllTransCat(){
        TransactionCategory[] transCats = TransactionCategory.getAll();
        
        return new ResponseEntity<>(transCats,OK);
    }
    
    @GetMapping("/PageStatus/getAll")
    public ResponseEntity<?> getAllPageStatus(){
        PageStatus[] pageStatusCat = PageStatus.getAll();
        
        return new ResponseEntity<>(pageStatusCat,OK);
    }
    
    
    @PostMapping("/businessCat/create")
    public ResponseEntity<?> createBusinessCat(@Valid @RequestBody BusinessCategory businessCategory , Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}
        businessCategoryService.save(businessCategory);
        
        return new ResponseEntity<>(null,OK);
    }
    
    @PostMapping("/businessCat/edit")
    public ResponseEntity<?> editBusinessCat(@Valid @RequestBody BusinessCategory businessCategory , Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}
        businessCategoryService.save(businessCategory);
        
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/businessCat/getBusinessCatById/{id}")
    public ResponseEntity<?> getBusinessCatById(@PathVariable("id") Long id){
        BusinessCategory businessCategory = businessCategoryService.findById(id);
        
        return new ResponseEntity<>(businessCategory,OK);
    }
    
    @GetMapping("/businessCat/getBusinessCatByName/{name}")
    public ResponseEntity<?> getBusinessCatByName(@PathVariable("name") String name){
        BusinessCategory businessCategory = businessCategoryService.findByName(name);
        
        return new ResponseEntity<>(businessCategory,OK);
    }
    
    @GetMapping("/businessCat/getAll")
    public ResponseEntity<?> getAllBusinessCategories(){
        List<BusinessCategory> businessCategories = businessCategoryService.findAll();
        
        return new ResponseEntity<>(businessCategories,OK);
    }
    
    @GetMapping("/businessCat/delete/{id}")
    public ResponseEntity<?> deleteBusinessCategory(@PathVariable("id") Long id , Principal principal){

        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (userPrincipal.getRole().equals("ROLE_ADMIN")){businessCategoryService.deleteById(id);}

        
        return new ResponseEntity<>(null,OK);
    }
    
    
}
