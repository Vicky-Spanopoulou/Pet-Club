package cb13.project.service;

import cb13.project.entities.Pet;

import java.util.List;

public interface PetService {

    List<Pet> findAllPets();

    Pet updatePet(Pet pet);

    Pet findById(long id);

    void deletePetById(Long petId);

    Pet savePet(Pet pet);


    Pet insertPet(Pet pet);
}
