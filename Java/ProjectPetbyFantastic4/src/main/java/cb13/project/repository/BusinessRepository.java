package cb13.project.repository;
import cb13.project.dto.BusinessAvgRatingDTO;
import cb13.project.dto.BusinessDTO;
import cb13.project.entities.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessRepository extends JpaRepository<Business ,Long> {

    @Query(value = "select distinct business.id, business.description, business.email, business.facebook, business.image_background, business.image_logo, business.instagram, business.name, business.web_page, business.phone, business.text, business.address_id, business.business_category_id,business.status ,IFNULL((select cast(avg(rating_number) as decimal (2,1)) from rating  group by rating.business_id  having business_id = business.id ),0) as avg_rating from business left join rating on business.id = rating.business_id WHERE business.status = :status",nativeQuery = true)
    List<Business> findBusinessByStatus(String status);

    @Query(value = "select count(status) as status from business where status = 'UNCHECKED'",nativeQuery = true)
    int findNumberUncheckedBusiness();

    @Query(value = "select distinct business.id, business.description, business.email, business.facebook, business.image_background, business.image_logo, business.instagram, business.name, business.web_page, business.phone, business.text, business.address_id, business.business_category_id,business.status ,IFNULL((select cast(avg(rating_number) as decimal (2,1)) from rating  group by rating.business_id  having business_id = business.id ),0) as avg_rating from business left join rating on business.id = rating.business_id",nativeQuery = true)
    List<Business> findAllBusinessAvgRating();

    @Query(value = "select business.id, business.description, business.email, business.facebook, business.image_background, business.image_logo, business.instagram, business.name, business.web_page, business.phone, business.text, business.address_id, business.business_category_id,business.status ,IFNULL((select cast(avg(rating_number) as decimal (2,1)) from rating  group by rating.business_id  having business_id = business.id ),0) as avg_rating from business left join rating on business.id = rating.business_id WHERE business.id = :id",nativeQuery = true)
    Business getByIdWithAvg(Long id);
}
