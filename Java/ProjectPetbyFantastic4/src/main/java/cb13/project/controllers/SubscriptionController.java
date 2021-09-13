package cb13.project.controllers;

import cb13.project.entities.Subscription;
import cb13.project.entities.User;
import cb13.project.service.SubscriptionService;
import cb13.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/subscriptions")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createSubscription(@Valid @RequestBody Subscription subscription ,Principal principal){
        User user =userService.findUserByUsername(principal.getName());
        if(!user.getRole().equals("ROLE_ADMIN")){return null;}
        subscriptionService.saveSubscription(subscription);
        return new ResponseEntity<>(null,OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editSubscription(@Valid @RequestBody Subscription subscription,Principal principal){
        User user =userService.findUserByUsername(principal.getName());
        if(!user.getRole().equals("ROLE_ADMIN")){return null;}
        subscriptionService.saveSubscription(subscription);
        return new ResponseEntity<>(null,OK);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSubscriptions(){
        List<Subscription> allSubscriptions = subscriptionService.findAllSubscriptions();
        return new ResponseEntity<>(allSubscriptions,OK);
    }
    
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getSubscriptionById(@PathVariable("id") Long subscriptionId ,Principal principal){
        Subscription subscription = subscriptionService.findById(subscriptionId);
        return new ResponseEntity<>(subscription,OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("id") Long subscriptionId,Principal principal){
        User user =userService.findUserByUsername(principal.getName());
        if(!user.getRole().equals("ROLE_ADMIN")){return null;}
        subscriptionService.deleteSubscriptionById(subscriptionId);
        return new ResponseEntity<>(null,OK);
    }
}
