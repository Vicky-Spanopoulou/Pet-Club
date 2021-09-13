package cb13.project.service.impl;


import cb13.project.entities.User;
import cb13.project.domain.UserPrincipal;
import cb13.project.enumeration.Role;
import cb13.project.exception.domain.*;
import cb13.project.repository.UserRepository;
import cb13.project.service.EmailService;
import cb13.project.service.LoginAttemptService;
import cb13.project.service.UserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import static cb13.project.constant.UserImplConstant.*;
import cb13.project.entities.Ads;
import cb13.project.entities.Business;
import cb13.project.entities.Pet;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        } else {
            try {
                validateLoginAttempt(user);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            userRepository.save(user);


            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;

        }
    }




    @Override
    public User saveUser(User user ) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException {
        validateNewUsernameAndEmail(EMPTY, user.getUsername(), user.getEmail());
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encodePassword(user.getPassword()));
        newUser.setPhone(user.getPhone());
        newUser.setAddress(user.getAddress());
        newUser.setActive(user.isActive());
        newUser.setNotLocked(user.isNotLocked());
        newUser.setRole(user.getRole());
        newUser.setAuthorities(Role.valueOf(user.getRole()).getAuthorities());
        newUser.setInvoiceDetails(user.getInvoiceDetails());
        newUser.setBusiness(user.getBusiness());
        newUser.setImg("");
        newUser.setVerifyCode(user.getVerifyCode());
        newUser.setVerified(user.isVerified());
        newUser.setAdvForEver(user.isAdvForEver());
        newUser=userRepository.save(newUser);
        emailService.sendVerificationEmail(user.getFirstName(),user.getPassword() ,user.getEmail(),user.getVerifyCode());
        return newUser;
    }



    @Override
    public User updateUser(User user) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {

        User currentUser = userRepository.findUserByUsername(user.getUsername());


        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());

        currentUser.setPhone(user.getPhone());
        currentUser.setAddress(user.getAddress());
        currentUser.setInvoiceDetails(user.getInvoiceDetails());
        currentUser.setActive(user.isActive());
        currentUser.setNotLocked(user.isNotLocked());
        currentUser.setRole(user.getRole());
        
        System.out.println(user.getRole());
        
        currentUser.setAuthorities(Role.valueOf(user.getRole()).getAuthorities());
        currentUser.setImg(user.getImg());
        currentUser.setVerifyCode(user.getVerifyCode());
        currentUser.setVerified(user.isVerified());
        currentUser.setBusiness(user.getBusiness());
        currentUser.setSub_until(user.getSub_until());
        currentUser.setAdvForEver(user.isAdvForEver());
        currentUser=userRepository.save(currentUser);
        return currentUser;
    }

    @Override
    public List<User> findClientsByDoctorId(Long id) {
        return userRepository.findClientsByDoctorId(id);
    }

    @Override
    public void resetPassword(String email ) throws MessagingException, EmailNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + email);
        }
        String password = generatePassword();
        user.setPassword(encodePassword(password));
        userRepository.save(user);
        emailService.sendPasswordEmail(user.getFirstName(), password, user.getEmail());
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }



    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByVerifyCode(String verifyCode) {
        return  userRepository.findUserByVerifyCode(verifyCode);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByRole(String role) {
        return userRepository.findUserByRole(role);
    }


    @Override
    public void deleteUser(String username) throws IOException {
        User user = userRepository.findUserByUsername(username);
        userRepository.deleteById(user.getId());
    }



    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }



    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }





    private void validateLoginAttempt(User user) throws MessagingException {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {

                user.setNotLocked(false);
                emailService.createUserBlockedEmail(user.getEmail(),"Pet Club,- Email Blocked Account");
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if(StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getById(id);
                
    }

    @Override
    public User findUserByPet(Pet pet) {
        return userRepository.findUserByPets(pet);
    }

    @Override
    public User findUserByAd(Ads ad) {
        return userRepository.findUserByAds(ad);
    }

    @Override
    public User findUserByBusiness(Business business) {
       return userRepository.findUserByBusiness(business);
    }

    @Override
    public void updatePassword(String username, String password) throws UserNotFoundException {
        User user = userRepository.findUserByUsername(username);
        user.setPassword(encodePassword(password));
        userRepository.save(user);
    }

    @Override
    public void updatePassAdmin(String username,String password ) throws MessagingException, EmailNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new EmailNotFoundException(NO_USER_FOUND_BY_EMAIL + username);
        }
        user.setPassword(encodePassword(password));
        userRepository.save(user);
    }


}
