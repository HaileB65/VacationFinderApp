package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.security.PasswordEncoder;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showWelcomePage(Model model) {
        return "welcome";
    }

    @GetMapping("/login")
    public String login() {
        return "login2";
    }

    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("newUser", new User());

        return "register";
    }

    @PostMapping("/process_register")
    public String processRegistration(User newUser, Model model) {
        userService.createNewUser(newUser);
        return "successful-registration";

    }

}
