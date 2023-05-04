package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.User;
import Capstone.Project.VacationFinderApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


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
        return "login";
    }

    /**
     * Shows register page.
     *
     * @param model adds empty user object to view.
     * @return displays register page.
     */
    @GetMapping("/register")
    public String register(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String addUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.createNewUser(user);
        return "successful-registration";
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
