package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Creates a new user.
     *
     * @param model adds empty user to view.
     * @return displays new-user page.
     */
    @GetMapping("/newUser")
    public String createNewUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new-user";
    }

    /**
     * Saves newly created user.
     *
     * @param newUser new user to be saved.
     * @return redirects to users page.
     */
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User newUser) {
        userService.createNewUser(newUser);
        return "redirect:/users";
    }

    /**
     * Shows user's home page.
     *
     * @return displays user-home-page page.
     * @throws Exception
     */
    @GetMapping("/userHomePage")
    public String showUserHomePage() throws Exception {

        return "user-home-page";
    }
}
