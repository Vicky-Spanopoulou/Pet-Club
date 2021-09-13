package cb13.project.repository;

import cb13.project.entities.PetCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetCategoryRepository extends JpaRepository<PetCategory ,Long> {
    
    PetCategory findPetCategoryByName(String Name);
}
