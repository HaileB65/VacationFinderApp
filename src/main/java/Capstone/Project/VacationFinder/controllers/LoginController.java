package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.User;
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

    /**
     * Shows welcome page.
     *
     * @return displays welcome page.
     */
    @GetMapping("/")
    public String showWelcomePage() {
        return "welcome";
    }

    /**
     * Shows login page.
     *
     * @return displays login page.
     */
    @GetMapping("/login")
    public String login() {
        return "login2";
    }

    /**
     * Shows register page.
     *
     * @param model adds empty user object to view.
     * @return displays register page.
     */
    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("newUser", new User());

        return "register";
    }

    /**
     * Saves new user.
     *
     * @param newUser new user to be saved.
     * @return displays successful-registration page.
     */
    @PostMapping("/process_register")
    public String processRegistration(User newUser) {
        userService.createNewUser(newUser);
        return "successful-registration";

    }

}
