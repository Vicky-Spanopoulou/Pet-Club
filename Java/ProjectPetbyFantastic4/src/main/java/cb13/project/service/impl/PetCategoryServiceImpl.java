package cb13.project.service.impl;

import cb13.project.entities.PetCategory;
import cb13.project.repository.PetCategoryRepository;
import cb13.project.service.PetCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetCategoryServiceImpl implements PetCategoryService {

    @Autowired
    private PetCategoryRepository petCategoryRepository;

    @Override
    public PetCategory savePetCategory(PetCategory petCategory) {
        return petCategoryRepository.save(petCategory);
    }

    @Override
    public List<PetCategory> findAllPetCategories() {
        return petCategoryRepository.findAll();
    }

    @Override
    public PetCategory updatePetCategory(PetCategory petCategory) {
        return petCategoryRepository.save(petCategory);
    }

    @Override
    public void deletePetCategoryById(Long petCategoryId) {
        petCategoryRepository.deleteById(petCategoryId);
    }

    @Override
    public PetCategory findPetCategoryById(Long id) {
        return petCategoryRepository.getById(id);
    }
    @Override
    public PetCategory findPetCategoryByName(String name) {
        return petCategoryRepository.findPetCategoryByName(name);
    }
}
