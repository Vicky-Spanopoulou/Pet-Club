package cb13.project.repository;

import cb13.project.entities.Ads;
import cb13.project.entities.Business;
import cb13.project.entities.Pet;
import cb13.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByPets(Pet pet);
    
    User findUserByVerifyCode(String verifiedCode);
    
    User findUserByAds(Ads ad);
    
    User findUserByBusiness(Business business);

    User findUserByRole(String role);

    @Query(value = "select * from users where users.id in (select distinct pet.user_id as id_pelaton from pet where pet.id in (select pet_id from examination_record as exam where exam.user_id=:doctorId))",nativeQuery = true)
    List<User> findClientsByDoctorId(@Param("doctorId")Long id);
}
