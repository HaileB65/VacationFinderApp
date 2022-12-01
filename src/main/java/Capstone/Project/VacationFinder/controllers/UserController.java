package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String showHomePage(Model model){
        List<User> usersTable = userService.getAllUsers();
        model.addAttribute("usersTable", usersTable);

        return "users-page";
    }

    @GetMapping("/newUser")
    public String createNewUser(Model model){
        model.addAttribute("newUser", new User());
        return "new-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser")User newUser){
        userService.createNewUser(newUser);
        return "redirect:/users";
    }

}
