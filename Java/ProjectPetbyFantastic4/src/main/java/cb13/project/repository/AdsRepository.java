package cb13.project.repository;

import cb13.project.entities.Ads;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ads,Long> {
}
