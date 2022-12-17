package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.*;
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

import java.util.List;

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

    @GetMapping("/home")
    public String showHomePage(Model model) throws Exception {
        Trip trip = tripService.getTripById(1L);
        model.addAttribute("trip", trip);
        return "home";
    }

    @GetMapping("/home2")
    public String showHomePage2(Model model) throws Exception {
        User user = userService.getUserById(1L);
        model.addAttribute("user", user);
        return "home2";
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

    @GetMapping("/vacationSpotFinder")
    public String showVacationSpotFinder(Model model) {
        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "vacation-spot-finder";
    }

    @PostMapping("/resultsPage")
    public String showQuestionnaireResultsPage(@ModelAttribute("questionnaire") Questionnaire questionnaire, Model model) {
        model.addAttribute("questionnaire", questionnaire);

        List<Destination> destinationsTable = destinationService.getDestinationByScenery(questionnaire.getFavoriteScenery());
        model.addAttribute("destinationsTable", destinationsTable);

        return "results-page";
    }


}
