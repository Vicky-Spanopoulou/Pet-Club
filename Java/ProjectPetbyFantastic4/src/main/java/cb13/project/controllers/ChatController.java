package cb13.project.controllers;

import cb13.project.entities.User;
import cb13.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@RestController
@RequestMapping(path = { "/chat"})
public class ChatController {

    @Autowired
    UserService userService;

    private List<User> admins = new ArrayList<>();

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public List<User> listUsers() {
        return getAdminUsers();
    }


    private List<User> getAdminUsers() {
        List<User> users = userService.getUsers();
        for(User user: users){
            if(user.getRole()=="ROLE_ADMIN"){
                admins.add(user);
            }
        }
        return admins;
    }


}
