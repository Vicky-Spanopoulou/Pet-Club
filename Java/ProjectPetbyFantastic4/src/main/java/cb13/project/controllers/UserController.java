package cb13.project.controllers;

import cb13.project.entities.User;
import cb13.project.domain.HttpResponse;
import cb13.project.domain.UserPrincipal;
import cb13.project.exception.domain.*;
import cb13.project.service.*;

import cb13.project.utility.GenerateVerificationCode;
import cb13.project.utility.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import static cb13.project.constant.SecurityConstant.JWT_TOKEN_HEADER;

import cb13.project.dto.UpdatePassDTO;
import cb13.project.dto.UserDTO;
import cb13.project.entities.Address;
import cb13.project.entities.Ads;
import cb13.project.entities.Business;
import cb13.project.entities.BusinessCategory;
import cb13.project.entities.InvoiceDetails;
import cb13.project.entities.City;
import cb13.project.entities.Pet;
import cb13.project.enumeration.PageStatus;
import cb13.project.enumeration.Role;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@CrossOrigin("http://localhost:8081")
@RequestMapping(path = {"/", "/user"})
public class UserController {

    public static final String EMAIL_SENT = "An email with a new password was sent to: ";
    public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully";
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JWTTokenProvider jwtTokenProvider;

    @Resource
    FileStorageService storageService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, UserService userService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Autowired
    PetService petService;

    @Autowired
    AdsService adsService;

    @Autowired
    CityService cityService;

    @Autowired
    AddressService addressService;

    @Autowired
    BusinessService businessService;

    @Autowired
    BusinessCategoryService businessCatService;

    @Autowired
    InvoiceDetailsService invoiceService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException {
        String verificationCode = GenerateVerificationCode.codeGenerator(40);
        while (userService.findUserByVerifyCode(verificationCode) != null) {
            verificationCode = GenerateVerificationCode.codeGenerator(40);
        }
//      User's Addres
        City city = cityService.findCityByName(userDTO.getCity());
        Address address = new Address(userDTO.getStreet());
        address.setCity(city);
        Address savedAddres = addressService.saveAddress(address);
//      USer's invoice details
        Address invoiceAddress = new Address();
        invoiceAddress.setCity(city);
        Address savedInvoiceAddress = addressService.saveAddress(invoiceAddress);
        InvoiceDetails invoiceDetail = new InvoiceDetails();
        invoiceDetail.setAddress(savedInvoiceAddress);
        InvoiceDetails savedInvoiceDet = invoiceService.saveInvoiceDetails(invoiceDetail);
//      User's Business
        BusinessCategory businessCat = businessCatService.findById(1L);
        Address businessAddress = new Address();
        businessAddress.setCity(city);
        Address savedBusinessAddress = addressService.saveAddress(businessAddress);
        Business business = new Business(PageStatus.UNPAID.toString());
        business.setAddress(savedBusinessAddress);
        business.setBusinessCategory(businessCat);
        Business savedBusiness = businessService.saveBusiness(business);

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(),
                userDTO.getEmail(), userDTO.getPassword(), userDTO.getPhone(),
                verificationCode, userDTO.getUsername());

        user.setAddress(savedAddres);
        user.setInvoiceDetails(savedInvoiceDet);
        user.setBusiness(savedBusiness);
        user.setVerified(false);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(Role.ROLE_USER.toString());

//        is not advertised for ever(default value)
        user.setAdvForEver(false);

        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, OK);
    }

    //creates a new user from the dashboard
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException {
        String verificationCode = GenerateVerificationCode.codeGenerator(40);
        while (userService.findUserByVerifyCode(verificationCode) != null) {
            verificationCode = GenerateVerificationCode.codeGenerator(40);
        }
//      User's Addres
        City city = cityService.findCityByName(userDTO.getCity());
        Address address = new Address(userDTO.getStreet());
        address.setCity(city);
        Address savedAddres = addressService.saveAddress(address);
//      USer's invoice details
        Address invoiceAddress = new Address();
        invoiceAddress.setCity(city);
        Address savedInvoiceAddress = addressService.saveAddress(invoiceAddress);
        InvoiceDetails invoiceDetail = new InvoiceDetails();
        invoiceDetail.setAddress(savedInvoiceAddress);
        InvoiceDetails savedInvoiceDet = invoiceService.saveInvoiceDetails(invoiceDetail);
//      User's Business
        BusinessCategory businessCat = businessCatService.findById(1L);
        Address businessAddress = new Address();
        businessAddress.setCity(city);
        Address savedBusinessAddress = addressService.saveAddress(businessAddress);
        Business business = new Business(PageStatus.UNPAID.toString());
        business.setAddress(savedBusinessAddress);
        business.setBusinessCategory(businessCat);
        Business savedBusiness = businessService.saveBusiness(business);

        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
                userDTO.getPassword(), userDTO.getPhone(), verificationCode, userDTO.getUsername());

        user.setAddress(savedAddres);
        user.setInvoiceDetails(savedInvoiceDet);
        user.setBusiness(savedBusiness);
//        is not advertised for ever(default value)
        user.setAdvForEver(userDTO.isAdvForEver());
        user.setVerified(userDTO.isVerified());
        user.setActive(userDTO.isActive());
        user.setNotLocked(userDTO.isIsNotLocked());
        user.setRole(userDTO.getRole());


        User newUser = userService.saveUser(user);
        return new ResponseEntity<>(newUser, OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user) throws IOException, NotAnImageFileException {

        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());

        //If subscribtion ends and the user is not admin then change role to ROLE_USER
        if (loginUser.getSub_until() != null
                && loginUser.getSub_until().before(new Date())
                && !loginUser.getRole().equals("ROLE_ADMIN")) {

            //If he bought in the past a subscription with advForEver, then his
            //role stays ROLE_ADVERTISED 
            if (loginUser.isAdvForEver()) {
                loginUser.setRole(Role.ROLE_ADVERTISED.toString());
            } // Else if user's subscribtion ended and hasn't got the advForEver true,
            // And his page is UNCHECKED, we want to set his role as ROLE_USER and
            // keep his page as UNCHECKED!!!!!
            else if (!loginUser.isAdvForEver() && loginUser.getBusiness().getStatus().equals(PageStatus.UNCHECKED.toString())) {
                loginUser.setRole(Role.ROLE_USER.toString());
            } // Else set user's role as ROLE_USER and unpublish(was checked and published) his business page
            else {
                loginUser.setRole(Role.ROLE_USER.toString());
                Business business = loginUser.getBusiness();
                business.setStatus(PageStatus.UNPUBLISHED.toString());
                businessService.saveBusiness(business);
            }
            try {
                userService.updateUser(loginUser);
            } catch (UserNotFoundException | UsernameExistException | EmailExistException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // If user has verified his email and is not blocked by admin
        if (loginUser.isVerified() == true && loginUser.isNotLocked()) {
            UserPrincipal userPrincipal = new UserPrincipal(loginUser);
            HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
            return new ResponseEntity<>(loginUser, jwtHeader, OK);
        } else if (loginUser.isVerified() && !loginUser.isNotLocked()) {

            return new ResponseEntity<>(null, HttpStatus.LOCKED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO userDTO, Principal principal) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, NotAnImageFileException {
        User userPrincipal = userService.findUserByUsername(principal.getName());

        if (userDTO.getUsername().equals(userPrincipal.getUsername()) || userPrincipal.getRole().equals("ROLE_ADMIN")) {
            City city = cityService.findCityByName(userDTO.getCity());
            Address address = addressService.findById(userDTO.getAddress());
            address.setCity(city);
            address.setStreet(userDTO.getStreet());
            Address updatedAddress = addressService.saveAddress(address);
            User user = userService.findUserByUsername(userDTO.getUsername());
            user.setAddress(updatedAddress);
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPhone(userDTO.getPhone());
            user.setImg(userDTO.getImg());

            //FOR ADMIN - NULL POINTER
            user.setNotLocked(userDTO.isIsNotLocked());
            user.setVerified(userDTO.isVerified());
            if (userDTO.getRole() != null) {
                user.setRole(userDTO.getRole());
            }
            user.setActive(userDTO.isActive());
            user.setSub_until(userDTO.getSub_until());
            user.setAdvForEver(userDTO.isAdvForEver());
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, OK);
        }
         return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/verify/{vc}")
    public void verifyUser(@PathVariable("vc") String code, HttpServletResponse resp) throws UserNotFoundException, EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User user = userService.findUserByVerifyCode(code);
        if (user != null) {
            user.setVerified(true);
            userService.updateUser(user);
            resp.sendRedirect("http://localhost:4200/login");
            ;
        } else {
            resp.sendRedirect("http://localhost:4200/error");
        }

    }

    @PostMapping("/updatePass")
    public ResponseEntity<?> updatePass(@Valid @RequestBody UpdatePassDTO updatePassDTO, Principal principal) throws IOException, NotAnImageFileException {
        User principalUser = userService.findUserByUsername(principal.getName());
        if (!principalUser.getUsername().equals(updatePassDTO.getUsername())) {
            return null;
        } else {
            authenticate(updatePassDTO.getUsername(), updatePassDTO.getOldPass());
            try {
                userService.updatePassword(updatePassDTO.getUsername(), updatePassDTO.getNewPass());
            } catch (UserNotFoundException ex) {

            }
            return new ResponseEntity<>("Updated", OK);
        }
    }

    @PostMapping("/resetPasswordAdmin/{username}")
    public ResponseEntity<HttpResponse> resetPasswordAdmin(@PathVariable("username") String username, @RequestBody String newPassword, Principal principal) throws MessagingException, EmailNotFoundException {
       User principalUser= userService.findUserByUsername(principal.getName());
        if (!principalUser.getRole().equals("ROLE_ADMIN")) {
            return null;
        } else {
            userService.updatePassAdmin(username, newPassword);
            return response(OK, EMAIL_SENT + username);
        }
    }


    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateEmail(@Valid @RequestBody User givenUser,Principal principal) throws IOException, NotAnImageFileException {
        if(!givenUser.getUsername().equals(principal.getName())){
            return null;
        }else{
        authenticate(givenUser.getUsername(), givenUser.getPassword());
        User user = userService.findUserByUsername(givenUser.getUsername());
        user.setEmail(givenUser.getEmail());
        try {
            userService.updateUser(user);
        } catch (UserNotFoundException | UsernameExistException | EmailExistException ex) {

        }
        return new ResponseEntity<>("Updated", OK);}
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<?> getUsername(@PathVariable("username") String username) {

        return new ResponseEntity<>(userService.findUserByUsername(username), OK);
    }

    @GetMapping("/getUserByMail/{email}")
    public ResponseEntity<?> getUserByMail(@PathVariable("email") String email) {

        return new ResponseEntity<>(userService.findUserByEmail(email), OK);
    }

    @GetMapping("/getRoles")
    public ResponseEntity<?> getRoles() {
        Role[] roles = Role.getAllRoles();
        return new ResponseEntity<>(roles, OK);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(Principal principal) {
        User user = userService.findUserByUsername(principal.getName());
        if (!user.getRole().equals("ROLE_ADMIN")) {
            return null;
        }
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, OK);


    }

    @GetMapping("/getByPet/{id}")
    public ResponseEntity<?> getUserByPetId(@PathVariable("id") Long petId) {

        Pet pet = petService.findById(petId);

        User user = userService.findUserByPet(pet);

        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/getUserByAd/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long adId) {

        Ads ad = adsService.findById(adId);
        User user = userService.findUserByAd(ad);

        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/getUserByBusiness/{id}")
    public ResponseEntity<?> getUserByBusiness(@PathVariable("id") Long businessId) {

        Business business = businessService.findById(businessId);
        User user = userService.findUserByBusiness(business);

        return new ResponseEntity<>(user, OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId,Principal principal) throws IOException, UserNotFoundException, UsernameExistException, EmailExistException, NotAnImageFileException {

        User user = userService.findUserById(userId);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(user.getUsername().equals(principal.getName()) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        user.setActive(false);
        Business business = businessService.findById(user.getBusiness().getId());
        business.setStatus("UNPAID");
        businessService.saveBusiness(business);
        }
        return new ResponseEntity<>("ok", OK);
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email,Principal principal) throws MessagingException, EmailNotFoundException {
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getEmail().equals(email) || userPrincipal.getRole().equals("ROLE_ADMIN")){
        userService.resetPassword(email);
            return response(OK, EMAIL_SENT + email);
        }
        return null;
    }


    @GetMapping(path = "getClients/{id}")
    public ResponseEntity<?> getAllClients(@PathVariable("id") Long id,Principal principal) {
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId()!=id){
            return null;
        }
        return new ResponseEntity<>(userService.findClientsByDoctorId(id), OK);
    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
