package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.User;
import Capstone.Project.VacationFinderApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") long userId) throws Exception {
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        System.out.println("User deleted");
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

    @GetMapping("/users")
    public String showUsersTable(@ModelAttribute("user") User user1, Model model){

        List<User> usersList = userService.getAllUsers();
        model.addAttribute("usersList", usersList);

        User user = new User();
        model.addAttribute("user", user);

        if (user1.getUsername() != null) {
//            User dbUser = userService.getByUserName(user1.getUsername());
//            Object[] trips = dbUser.getTrips().toArray();
//            model.addAttribute("userTrips" , trips);
            System.out.println("test");
        }

        return "users";
    }

    @GetMapping("/unlockUser/{userId}")
    public String unlockUser(@PathVariable("userId") long userId) throws Exception {
        User user = userService.getUserById(userId);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("/lockUser/{userId}")
    public String lockUser(@PathVariable("userId") long userId) throws Exception {
        User user = userService.getUserById(userId);
        user.setEnabled(false);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpired(false);
        userService.saveUser(user);

        return "redirect:/users";
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
        return "redirect:/welcome";
    }
}
