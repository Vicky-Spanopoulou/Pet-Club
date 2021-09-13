package cb13.project.controllers;


import cb13.project.dto.EmailDTO;
import cb13.project.entities.User;
import cb13.project.service.EmailService;
import cb13.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/email")
public class MailController {

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> SendMail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailService.createPageEmail(emailDTO.getSender(), emailDTO.getSubject(),emailDTO.getFullMessage());

        return new ResponseEntity<>("asd",OK);
    }

    @PostMapping("/newsLetter")
    public ResponseEntity<?> SendNewsLetterMail(@RequestBody EmailDTO emailDTO) throws MessagingException {
        emailService.createNewsLetterEmail(emailDTO.getMessage());
        return new ResponseEntity<>("asd",OK);

    }


}
