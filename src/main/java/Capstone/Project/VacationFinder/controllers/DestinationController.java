package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.Questionnaire;
import Capstone.Project.VacationFinder.models.Trip;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.DestinationService;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;


    /**
     * Shows all destinations.
     *
     * @param currentUser current user signed in.
     * @param model adds a list of all destinations to view.
     * @return displays destinations page.
     * @throws Exception
     */
    @GetMapping("/destinations")
    public String showDestinationsPage(@AuthenticationPrincipal User currentUser, Model model) throws Exception {
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        return "destinations";
    }

    /**
     * Shows a specific destination's home page.
     *
     * @param destinationId ID of destination to be shown
     * @param model adds destination object, name, and images to view
     * @return displays a specific destination page
     * @throws Exception
     */
    @GetMapping("/destination/{destinationId}")
    public String showDestinationHomePage(@PathVariable("destinationId") long destinationId, Model model) throws Exception {

        Destination destination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destinationName", destination.getName());
        model.addAttribute("destination", destination);

        ArrayList<String> images = new ArrayList<>();
        images.add(destination.getImage1());
        images.add(destination.getImage2());
        model.addAttribute("images", images);

        return "destination";
    }

    /**
     * Creates a new destination.
     *
     * @param model adds an empty destination object to view.
     * @return displays new-destination pagee.
     */
    @GetMapping("/newDestination")
    public String ShowNewDestinationPage(Model model) {
        model.addAttribute("newDestination", new Destination());
        return "new-destination";
    }

    /**
     * Shows destination finder page.
     *
     * @param model adds empty questionnaire to view.
     * @return displays destination-finder page.
     */
    @GetMapping("/destinationFinder")
    public String showDestinationFinderPage(Model model) {
        model.addAttribute("questionnaire", new Questionnaire());
        return "destination-finder";
    }

    /**
     * Saves a new destination.
     *
     * @param destination destination to be saved.
     * @return redirects to destinations page.
     */
    @PostMapping("/saveDestination")
    public String saveDestination(@ModelAttribute("newDestination") Destination destination) {
        destinationService.createNewDestination(destination);
        return "redirect:/destinations";
    }

    /**
     * Shows destination finder results.
     *
     * @param questionnaire
     * @param model
     * @return
     */
    @PostMapping("/destinationFinderResults")
    public String showDestinationFinderResultsPage(@ModelAttribute("questionnaire") Questionnaire questionnaire, Model model) {
        model.addAttribute("questionnaire", questionnaire);

        List<Destination> destinationsMatchingWeatherAndScenery = destinationService.getByWeatherAndScenery(questionnaire.getWeather(), questionnaire.getFavoriteScenery());
        model.addAttribute("searchedDestinations", destinationsMatchingWeatherAndScenery);


        List<Destination> remainingDestinations = destinationService.getByWeather(questionnaire.getWeather());
        for (Destination destination : destinationsMatchingWeatherAndScenery) {
            remainingDestinations.remove(destination);
        }
        model.addAttribute("remainingDestinations", remainingDestinations);

        return "destination-finder-results";
    }


}
