package cb13.project.repository;

import cb13.project.entities.Rating;
import cb13.project.entities.User;
import cb13.project.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    Rating findByUserAndBusiness(User user, Business business);

    @Query(value=" select cast(avg(rating_number) as decimal (2,1)) from rating where business_id = :businessId",nativeQuery = true)
    float avgBusinessRating(@Param("businessId") Long businessId);
}
