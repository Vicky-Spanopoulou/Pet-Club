/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.TransactionDTO;
import cb13.project.entities.Address;
import cb13.project.entities.Business;
import cb13.project.entities.City;
import cb13.project.entities.Transaction;
import cb13.project.entities.User;
import cb13.project.enumeration.PageStatus;
import cb13.project.enumeration.Role;
import cb13.project.exception.domain.EmailExistException;
import cb13.project.exception.domain.NotAnImageFileException;
import cb13.project.exception.domain.UserNotFoundException;
import cb13.project.exception.domain.UsernameExistException;
import cb13.project.service.*;

import java.io.IOException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vicky
 */
@RestController
@RequestMapping(path = "/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CityService cityService;

    @Autowired
    AddressService addressService;

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PostMapping("/create")
    public ResponseEntity<?> createAd(@Valid @RequestBody TransactionDTO transactionDTO)
            throws UserNotFoundException, UsernameExistException, EmailExistException,
            IOException, NotAnImageFileException, MessagingException {



        User user = userService.findUserById(transactionDTO.getUserId());
        Calendar calendar = Calendar.getInstance();
        Date today = new Date();
        calendar.setTime(today);
        calendar.add(Calendar.DATE, transactionDTO.getSub_duration());
        Date expirationDate = calendar.getTime();
        
        Business userBusiness = user.getBusiness();

        //create and save the transaction
        Transaction transaction = new Transaction(
                today,
                transactionDTO.getType(),
                transactionDTO.isPaid(),
                user.getUsername(),
                transactionDTO.getSub_name(),
                transactionDTO.getSub_price(),
                transactionDTO.getSub_duration(),
                transactionDTO.getSub_role(),
                transactionDTO.isAdvForEver()
        );

        if (transactionDTO.isPaid()) {
            user.setSub_until(expirationDate);
            user.setRole(transactionDTO.getSub_role());
            userBusiness.setStatus(PageStatus.UNCHECKED.toString());
            businessService.saveBusiness(userBusiness);

//          If user has NOT buy in the past subscription with isAdvForEver true
//          and the new subscription has got isAdvForEver true, then change the
//          user's advForEver to true, else let it false
            if(!user.isAdvForEver() && transaction.isAdvForEver()){
                user.setAdvForEver(true);
            }

//            if(user.getBusiness()== null){
//                Business business = new Business(PageStatus.UNCHECKED.toString());
//                Address address = new Address();
//                City city = cityService.findAllCities().get(0);
//
//                address.setCity(city);
//                address = addressService.saveAddress(address);
//                business.setAddress(address);
//                businessService.saveBusiness(business);
//
//                user.setBusiness(business);
//
//            }
        }
        transaction.setPaypalId(transactionDTO.getPaypalId());
        user.addTransaction(transaction);
        userService.updateUser(user);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        String paid;
        if(savedTransaction.isPaid() == true){
            paid="PAID";
        }else{
            paid="On Process";
        }
        emailService.createTransactionEmail(user.getEmail(), savedTransaction.getDate(),user.getInvoiceDetails().getCompanyName(),
                user.getInvoiceDetails().getAddress().getStreet(),savedTransaction.getId(),
                user.getInvoiceDetails().getPhone(),savedTransaction.getType(),user.getInvoiceDetails().getCompanyEmail(),user.getInvoiceDetails().getFinancialService(),
                user.getInvoiceDetails().getVatNumber(),paid,savedTransaction.getSub_name(),
                savedTransaction.getSub_price(),savedTransaction.getSub_duration(),savedTransaction.getSub_role(),savedTransaction.isAdvForEver());
        return new ResponseEntity<>(savedTransaction, OK);
    }

    @PostMapping("/updatePayment")
    public ResponseEntity<?> updateTransactionPayment(@Valid @RequestBody TransactionDTO transactionDTO ,Principal principal)
            throws UserNotFoundException, UsernameExistException, EmailExistException,
            IOException, NotAnImageFileException {
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(!userPrincipal.getRole().equals("ROLE_ADMIN")){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        Date expirationDate = new Date();
        calendar.setTime(expirationDate);
        calendar.add(Calendar.DATE, transactionDTO.getSub_duration());
        expirationDate = calendar.getTime();
        Transaction transaction = transactionService.findById(transactionDTO.getId());
        User user = userService.findUserById(transactionDTO.getUserId());
        transaction.setPaid(transactionDTO.isPaid());
        Business userBusiness = user.getBusiness();
        
        if (transactionDTO.isPaid()) {
            user.setSub_until(expirationDate);
            user.setRole(transactionDTO.getSub_role());
            userBusiness.setStatus(PageStatus.UNCHECKED.toString());
            businessService.saveBusiness(userBusiness);
//          If user has NOT buy in the past subscription with isAdvForEver true
//          and the new subscription has got isAdvForEver true, then change the
//          user's advForEver to true, else let it false
            if(!user.isAdvForEver() && transaction.isAdvForEver()){
                user.setAdvForEver(true);
            }

            if(user.getBusiness()== null){
                Business business = new Business(PageStatus.UNCHECKED.toString());
                Address address = new Address();
                City city = cityService.findAllCities().get(0);

                address.setCity(city);
                address = addressService.saveAddress(address);
                business.setAddress(address);
                businessService.saveBusiness(business);

                user.setBusiness(business);

            }
        }
        else{
            user.setSub_until(null);
            user.setRole(Role.ROLE_USER.toString());
        }
        userService.updateUser(user);
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(null, OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTransactions(Principal principal) {
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(!userPrincipal.getRole().equals("ROLE_ADMIN")){return null;}
        List<Transaction> allTransactions = transactionService.findAllTransactions();
        return new ResponseEntity<>(allTransactions, OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable("id") Long transactionId ,Principal principal) {
        User userPrincipal = userService.findUserByUsername(principal.getName());

        Transaction transaction = transactionService.findById(transactionId);
        if(userPrincipal.getRole().equals("ROLE_ADMIN") || userPrincipal.getId() == transaction.getUser().getId()) {
            return new ResponseEntity<>(transaction, OK);
        }
        return null;
    }

}
