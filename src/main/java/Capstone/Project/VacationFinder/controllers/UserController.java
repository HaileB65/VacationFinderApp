package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.repositories.UserRepository;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/newUser")
    public String createNewUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new-user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("newUser") User newUser) {
        userService.createNewUser(newUser);
        return "redirect:/users";
    }

    @GetMapping("/userHomePage")
    public String showUserHomePage(Model model) throws Exception {
        User user = userService.getUserById(1L);
        model.addAttribute("user", user);

        ArrayList<Destination> desintations = new ArrayList<>();

        //TODO add a user trips to user home page

//        for(Trip trip : user.getTrips()){
//            desintations.add((Destination) trip.destinations);
//        }
//
//        List<Trip> trips = (List<Trip>) user.getTrips();
//        model.addAttribute("trips",trips);

        return "user-home-page";
    }
}
