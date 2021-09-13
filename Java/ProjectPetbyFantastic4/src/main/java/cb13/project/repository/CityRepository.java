package cb13.project.repository;

import cb13.project.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {

    public City findByName(String name);
}
