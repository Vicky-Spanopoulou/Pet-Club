package cb13.project.service;

import cb13.project.entities.PetCategory;

import java.util.List;

public interface PetCategoryService {

    PetCategory savePetCategory(PetCategory petCategory);

    List<PetCategory> findAllPetCategories();
    
    PetCategory findPetCategoryById(Long id);
    
    PetCategory findPetCategoryByName(String name);


    PetCategory updatePetCategory(PetCategory petCategory);

    void deletePetCategoryById(Long petCategoryId);
}
