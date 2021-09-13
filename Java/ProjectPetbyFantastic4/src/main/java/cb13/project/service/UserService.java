package cb13.project.service;

import cb13.project.entities.Ads;
import cb13.project.entities.Business;
import cb13.project.entities.Pet;
import cb13.project.entities.User;
import cb13.project.exception.domain.*;

import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User saveUser(User user ) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException;

    List<User> getUsers();

    User findUserByVerifyCode(String verifyCode);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByRole(String role);

    User findUserById(Long id);
    
    User findUserByPet(Pet pet);
    
    User findUserByAd(Ads ad);
    
    User findUserByBusiness(Business business);

    User updateUser(User user) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException;

    List<User> findClientsByDoctorId(Long id);

    void deleteUser(String username) throws IOException;

    void resetPassword(String email ) throws MessagingException, EmailNotFoundException, EmailNotFoundException;
    
    void updatePassword(String username, String password)throws UserNotFoundException;
    
    void updatePassAdmin(String username,String password)throws MessagingException, EmailNotFoundException;


}
