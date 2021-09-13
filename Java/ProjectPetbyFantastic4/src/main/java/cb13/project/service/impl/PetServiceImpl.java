package cb13.project.service.impl;

import cb13.project.entities.Pet;
import cb13.project.repository.PetRepository;
import cb13.project.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void deletePetById(Long petId) {
     petRepository.deleteById(petId);
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(long id) {
        return petRepository.getById(id);
    }

    @Override
    public Pet insertPet(Pet pet) {
        return petRepository.save(pet);
    }
}
