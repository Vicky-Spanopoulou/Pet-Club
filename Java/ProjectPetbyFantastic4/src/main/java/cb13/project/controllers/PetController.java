/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.PetDTO;
import cb13.project.entities.Pet;
import cb13.project.entities.PetCategory;
import cb13.project.entities.User;
import cb13.project.service.FileStorageService;
import cb13.project.service.PetCategoryService;
import cb13.project.service.PetService;
import cb13.project.service.UserService;

import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tsepe
 */
@RestController
@RequestMapping(path = "/pets")
@CrossOrigin("http://localhost:8081")
public class PetController {

    @Autowired
    FileStorageService storageService;

    @Autowired
    PetService petService;

    @Autowired
    UserService userService;

    @Autowired
    PetCategoryService petCatSer;

    @PostMapping("/create")
    public ResponseEntity<?> createPet(@Valid @RequestBody PetDTO petDTO) {

        User user = userService.findUserById(petDTO.getUser());
        PetCategory petCat = petCatSer.findPetCategoryByName(petDTO.getPetCategory().getName());

        Pet pet = new Pet(
                petDTO.getName(),
                petDTO.getBreed(),
                petDTO.getDob(),
                petDTO.getChipNumber(),
                petDTO.getColor(),
                petDTO.getBehavior(),
                petDTO.getSex(),
                null,
                user,
                petCat);
        Pet savedPet = petService.savePet(pet);
        return new ResponseEntity<>(savedPet, OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPets() {
        List<Pet> pets = petService.findAllPets();
        return new ResponseEntity<>(pets, OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPet(@PathVariable("id") Long petId) {
        Pet pet = petService.findById(petId);
        return new ResponseEntity<>(pet, OK);
    }

    
    @PostMapping("/edit")
    public ResponseEntity<?> editPet(@Valid @RequestBody PetDTO petDTO, Principal principal) {
        Pet pet = petService.findById(petDTO.getId());
        User user = new User();
        if (petDTO.getUser() != null) {
            user = userService.findUserById(petDTO.getUser());

        } else {
            user = userService.findUserByPet(pet);
        }

        PetCategory petCat = petCatSer.findPetCategoryByName(petDTO.getPetCategory().getName());
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (userPrincipal.getUsername() == user.getUsername() || userPrincipal.getRole().equals("ROLE_ADMIN")) {

            pet.setName(petDTO.getName());
            pet.setBreed(petDTO.getBreed());
            pet.setDob(petDTO.getDob());
            pet.setChipNumber(petDTO.getChipNumber());
            pet.setColor(petDTO.getColor());
            pet.setBehavior(petDTO.getBehavior());
            pet.setSex(petDTO.getSex());
            pet.setUser(user);
            pet.setPetCategory(petCat);
            petService.savePet(pet);
            return new ResponseEntity<>("DONE", OK);
        }

        return new ResponseEntity<>(null, OK);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deletePet(@PathVariable("id") Long petId, Principal principal) {
        Pet pet = petService.findById(petId);
        User user = pet.getUser();
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if (user.equals(userPrincipal) || userPrincipal.getRole().equals("ROLE_ADMIN")) {
            petService.deletePetById(petId);
            return new ResponseEntity<>("DONE", OK);
        }else{
        return new ResponseEntity<>(null, OK);}
    }

}
