package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.Checklist;
import Capstone.Project.VacationFinderApp.models.Destination;
import Capstone.Project.VacationFinderApp.models.Questionnaire;
import Capstone.Project.VacationFinderApp.models.User;
import Capstone.Project.VacationFinderApp.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    /**
     * Shows all destinations.
     *
     * @param model       adds a list of all destinations to view.
     * @return displays destinations page.
     * @throws Exception
     */
    @GetMapping("/destinations")
    public String showDestinationsPage(Model model) throws Exception {
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        return "destinations";
    }

    /**
     * Shows a specific destination's home page.
     *
     * @param destinationId ID of destination to be shown
     * @param model         adds destination object, name, and images to view
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

    @GetMapping("/destination/{destinationId}/{weather}/{scenery}")
    public String viewDestinationFromDestinationResultsPage(
            @PathVariable("destinationId") long destinationId,
            @PathVariable("weather") String weather,
            @PathVariable("scenery") String scenery, Model model) throws Exception {

        Destination destination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destinationName", destination.getName());
        model.addAttribute("destination", destination);

        ArrayList<String> images = new ArrayList<>();
        images.add(destination.getImage1());
        images.add(destination.getImage2());
        model.addAttribute("images", images);

        model.addAttribute("weather", weather);
        model.addAttribute("scenery", scenery);

        return "destination-from-results-page";
    }

    /**
     * Creates a new destination.
     *
     * @param model adds an empty destination object to view.
     * @return displays new-destination page.
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

    @GetMapping("/destinationFinderResults/{weather}/{scenery}")
    public String returnToDestinationFinderResultsPage(@PathVariable String weather, @PathVariable String scenery, Model model) {

        List<Destination> destinationsMatchingWeatherAndScenery = destinationService.getByWeatherAndScenery(weather, scenery);
        model.addAttribute("searchedDestinations", destinationsMatchingWeatherAndScenery);

        List<Destination> remainingDestinations = destinationService.getByWeather(weather);
        for (Destination destination : destinationsMatchingWeatherAndScenery) {
            remainingDestinations.remove(destination);
        }
        model.addAttribute("remainingDestinations", remainingDestinations);

        return "destination-finder-results";
    }

    @GetMapping("/editDestination/{destinationId}")
    public String editDestination(@PathVariable("destinationId") Long destinationId, Model model) throws Exception {
        Destination dbDestination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destination", dbDestination);
        model.addAttribute("destinationId", dbDestination.getId());
        return "edit-destination";
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
        List<Destination> destinationsMatchingWeatherAndScenery = destinationService.getByWeatherAndScenery(questionnaire.getPreferredWeather(), questionnaire.getPreferredScenery());
        model.addAttribute("searchedDestinations", destinationsMatchingWeatherAndScenery);

        List<Destination> remainingDestinations = destinationService.getByWeather(questionnaire.getPreferredWeather());
        for (Destination destination : destinationsMatchingWeatherAndScenery) {
            remainingDestinations.remove(destination);
        }
        model.addAttribute("remainingDestinations", remainingDestinations);

        model.addAttribute("weather", questionnaire.getPreferredWeather());
        model.addAttribute("scenery", questionnaire.getPreferredScenery());

        return "destination-finder-results";
    }

}
