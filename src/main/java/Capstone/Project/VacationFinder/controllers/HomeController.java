package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.services.DestinationService;
import Capstone.Project.VacationFinder.services.ItineraryService;
import Capstone.Project.VacationFinder.services.TripService;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;


    @GetMapping("/tripHomePage")
    public String showHomePage(Model model) throws Exception {
        User user = userService.getUserById(1L);
        model.addAttribute("user", user);

        return "trip-home-page";
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


    @GetMapping("/newTripPlanned")
    public String tripPlanned(@ModelAttribute("currentTripId") Long id, Model model) {
        model.addAttribute("currentTripId");
        return "trip-planned";
    }

    @GetMapping("/styledPage")
    public String getStyledPage(Model model) {
        model.addAttribute("name", "Baeldung Reader");
        return "cssandjs/styled-page";
    }

    @GetMapping("/trip/{tripId}")
    public String showTripHomePage(@PathVariable("tripId") long tripId, Model model) throws Exception {
        Trip trip = tripService.getTripById(tripId);

        model.addAttribute("destinationName", trip.destinations);
        model.addAttribute("destinationName", trip.getItinerary());
        model.addAttribute("destinationName", trip.getChecklist());

        return "trip-home-page";
    }
}

