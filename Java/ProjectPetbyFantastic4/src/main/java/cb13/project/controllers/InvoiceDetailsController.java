/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.InvoiceDetailsDTO;
import cb13.project.entities.Address;
import cb13.project.entities.City;
import cb13.project.entities.InvoiceDetails;
import cb13.project.entities.User;
import cb13.project.service.AddressService;
import cb13.project.service.CityService;
import cb13.project.service.InvoiceDetailsService;
import cb13.project.service.UserService;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author vicky
 */

@RestController
@RequestMapping(path = "/invoiceDetails")
public class InvoiceDetailsController {
    
    @Autowired
    InvoiceDetailsService invoiceDetailsService;


    
    @Autowired
    AddressService addressService;

    @Autowired
    CityService cityService;
    
    @Autowired
    UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoiceDetailsDTO invoiceDetailsDTO) {

        User user = userService.findUserById(invoiceDetailsDTO.getUserId());
        City city = cityService.findCityByName(invoiceDetailsDTO.getCity());
        Address address;
        if (invoiceDetailsDTO.getAddressId() == null){

            //create an address and save it
            address = new Address(
                    invoiceDetailsDTO.getStreet(),
                    invoiceDetailsDTO.getLongitude(),
                    invoiceDetailsDTO.getLatitude(),
                    invoiceDetailsDTO.getPostalCode()
            );
        }
        else{
            address = addressService.findById(invoiceDetailsDTO.getAddressId());
            address.setStreet(invoiceDetailsDTO.getStreet());
            address.setPostalCode(invoiceDetailsDTO.getPostalCode());
            address.setCity(city);
        }
        
            city.addAddress(address);
            Address savedAddress = addressService.saveAddress(address);
            
            

        //create and save the invoice details
        InvoiceDetails invoiceDetails = new InvoiceDetails(
                invoiceDetailsDTO.getCompanyName(),
                invoiceDetailsDTO.getCompanyEmail(),
                invoiceDetailsDTO.getFinancialService(),
                invoiceDetailsDTO.getVatNumber(),
                invoiceDetailsDTO.getPhone()
        );
        invoiceDetails.setAddress(savedAddress);
        invoiceDetails.setUser(user);
        invoiceDetailsService.saveInvoiceDetails(invoiceDetails);
        
        return new ResponseEntity<>(null, OK);
    }
    
    
    @PostMapping("/edit")
    public ResponseEntity<?> editInvoice(@Valid @RequestBody InvoiceDetailsDTO invoiceDetailsDTO , Principal principal) {

        InvoiceDetails invoice = invoiceDetailsService.findById(invoiceDetailsDTO.getId());
        User userPrincipal = userService.findUserByUsername(principal.getName());

        if(userPrincipal.getId() == invoice.getUser().getId() || userPrincipal.getRole().equals("ROLE_ADMIN")){

        Address address = invoice.getAddress();
        City city = cityService.findCityByName(invoiceDetailsDTO.getCity());

        invoice.setCompanyName(invoiceDetailsDTO.getCompanyName());
        invoice.setCompanyEmail(invoiceDetailsDTO.getCompanyEmail());
        invoice.setFinancialService(invoiceDetailsDTO.getFinancialService());
        invoice.setVatNumber(invoiceDetailsDTO.getVatNumber());
        invoice.setPhone(invoiceDetailsDTO.getPhone());
        
        address.setStreet(invoiceDetailsDTO.getStreet());
        address.setLongitude(invoiceDetailsDTO.getLongitude());
        address.setLatitude(invoiceDetailsDTO.getLatitude());
        address.setPostalCode(invoiceDetailsDTO.getPostalCode());
        
        city.addAddress(address);
        addressService.saveAddress(address);
        invoice.setAddress(address);
        
        InvoiceDetails updatedInv = invoiceDetailsService.saveInvoiceDetails(invoice);

        return new ResponseEntity<>(updatedInv, OK);}
        return null;
    }
    
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllInvoices(Principal principal){
//
//
//        List<InvoiceDetails> invoice = invoiceDetailsService.findAllInvoiceDetails();
//
//        return new ResponseEntity<>(invoice,OK);
//    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getInvoiceById (@PathVariable("id") Long invoiceId){
        
        InvoiceDetails invoice = invoiceDetailsService.findById(invoiceId);
        return new ResponseEntity<>(invoice,OK);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") Long invoiceId , Principal principal){
        InvoiceDetails inv = invoiceDetailsService.findById(invoiceId);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(inv.getId() == userPrincipal.getInvoiceDetails().getId()){
        invoiceDetailsService.deleteInvoiceDetailsById(invoiceId);}
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/getUserInvoice/{id}")
    public ResponseEntity<?> getInvoiceUserById (@PathVariable("id") Long userId , Principal principal){
        InvoiceDetails inv = invoiceDetailsService.findInvoiceByUserId(userId);
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(inv.getId() == userPrincipal.getInvoiceDetails().getId()){
            return new ResponseEntity<>(inv,OK);
        }
        return null;
    }
    
    
}
