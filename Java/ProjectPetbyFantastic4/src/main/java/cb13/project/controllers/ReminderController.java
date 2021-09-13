/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cb13.project.controllers;

import cb13.project.dto.ReminderDTO;
import cb13.project.entities.Reminder;
import cb13.project.entities.User;
import cb13.project.service.ReminderService;
import cb13.project.service.UserService;

import java.security.Principal;
import java.util.List;
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
@RequestMapping(path = "/reminder")
public class ReminderController {
    
    @Autowired
    ReminderService reminderService;
    
    @Autowired
    UserService userService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createReminder(@Valid @RequestBody ReminderDTO reminderDTO, Principal principal) {

        User user = userService.findUserById(reminderDTO.getUserId());
        if (!user.getUsername().equals(principal.getName())){return null;}
        //create and save the reminder
        Reminder reminder = new Reminder(
                reminderDTO.getInfo(),
                reminderDTO.getDone()
        );
//        user.addReminder(reminder);
        reminder.setUser(user);
        reminderService.saveReminder(reminder);
        
        return new ResponseEntity<>(null, OK);
    }
    
    
    @PostMapping("/edit")
    public ResponseEntity<?> editReminder(@Valid @RequestBody ReminderDTO reminderDTO,Principal principal) {

        Reminder reminder = reminderService.findById(reminderDTO.getId());
        User userPrincipal = userService.findUserByUsername(principal.getName());
        if(userPrincipal.getId() != reminder.getUser().getId()){ return null;}
        reminder.setInfo(reminderDTO.getInfo());
        reminder.setDone(reminderDTO.getDone());

        reminderService.saveReminder(reminder);
        return new ResponseEntity<>(null, OK);
    }
    
    
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllReminders(){
//
//        List<Reminder> reminders = reminderService.findAllReminders();
//
//        return new ResponseEntity<>(reminders,OK);
//    }
    
    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<?> getAllRemindersByUser(@PathVariable("id") Long userId ,Principal principal){

        User user = userService.findUserById(userId);
        if(!user.getUsername().equals(principal.getName())){return null;}
        else {
            List<Reminder> reminders = reminderService.findAllRemindersByUser(user);
            return new ResponseEntity<>(reminders,OK);
        }

    }
    
    
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReminderById (@PathVariable("id") Long id,Principal principal){
        User userPrincipal =  userService.findUserByUsername(principal.getName());
        Reminder reminder = reminderService.findById(id);
        if(userPrincipal.getId() != reminder.getUser().getId()){return null;}

        return new ResponseEntity<>(reminder,OK);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteReminder(@PathVariable("id") Long id,Principal principal){
        User userPrincipal =  userService.findUserByUsername(principal.getName());
        Reminder rem = reminderService.findById(id);
        if(userPrincipal.getId() != rem.getUser().getId()){return null;}
        reminderService.deleteReminderById(id); 
        return new ResponseEntity<>(null,OK);
    }
    
    
}
