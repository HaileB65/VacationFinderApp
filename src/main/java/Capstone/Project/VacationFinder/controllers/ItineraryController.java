package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Itinerary;
import Capstone.Project.VacationFinder.models.Trip;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.ItineraryService;
import Capstone.Project.VacationFinder.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItineraryController {

    @Autowired
    ItineraryService itineraryService;

    @Autowired
    TripService tripService;


    @GetMapping("/createItinerary")
    public String createNewItinerary(Model model) {
        model.addAttribute("newItinerary", new Itinerary());
        return "create-itinerary";
    }

    @GetMapping("/addItinerary")
    public String showAddItineraryPage(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        return "add-itinerary";
    }

    @PostMapping("/saveItinerary")
    public String saveItinerary(@ModelAttribute("newItinerary") Itinerary newItinerary) {
        itineraryService.createNewItinerary(newItinerary);
        return "redirect:/home";
    }

    @PostMapping("/addItineraryToTrip")
    public String showAddItineraryToTripPage(@ModelAttribute("itinerary") Itinerary itinerary, @AuthenticationPrincipal User currentUser, Model model) throws Exception {
        System.out.println("start of add Itinerary method");

        //TODO add itinerary to current trip being created.

        System.out.println("start of addItinerary() method");

        return "itinerary-added";
    }

}
