package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.Trip;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.DestinationService;
import Capstone.Project.VacationFinder.services.ItineraryService;
import Capstone.Project.VacationFinder.services.TripService;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    DestinationService destinationService;

    @Autowired
    ItineraryService itineraryService;

    @Autowired
    TripService tripService;

    @GetMapping("/tripHomePage")
    public String showHomePage(Model model) throws Exception {
        User user = userService.getUserById(1L);
        model.addAttribute("user", user);
        return "trip-home-page";
    }

    @GetMapping("/userHomePage")
    public String showUserHomePage(Model model) throws Exception {
        User user = userService.getUserById(1L);
        model.addAttribute("user", user);
        return "user-home-page";
    }

    @GetMapping("/newTrip")
    public String createTrip(Model model) throws Exception {
        Trip newTrip = new Trip();
        model.addAttribute("newTrip", newTrip);
        return "new-trip";
    }

    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("newTrip") Trip trip) {
        tripService.createNewTrip(trip);
        return "redirect:/home";
    }

    @GetMapping("/styledPage")
    public String getStyledPage(Model model) {
        model.addAttribute("name", "Baeldung Reader");
        return "cssandjs/styled-page";
    }

    @GetMapping("/addItinerary")
    public String showAddItineraryPage(Model model) {

        return "create-itinerary";
    }

    @PostMapping("/addDestination")
    public String addDestinationToTrip(Destination destination){

        destinationService.addToTrip(destination);


        return"destination-added";
    }

}

