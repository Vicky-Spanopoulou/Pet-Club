package cb13.project.repository;

import cb13.project.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long> {

    public Pet save(long id);


}
