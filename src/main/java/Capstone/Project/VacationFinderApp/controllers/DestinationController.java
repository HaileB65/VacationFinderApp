package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.Destination;
import Capstone.Project.VacationFinderApp.models.Questionnaire;
import Capstone.Project.VacationFinderApp.models.Trip;
import Capstone.Project.VacationFinderApp.services.DestinationService;
import Capstone.Project.VacationFinderApp.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TripService tripService;

    /**
     * Shows all destinations.
     *
     * @param model adds a list of all destinations to view.
     * @return displays destinations page.
     * @throws Exception
     */
    @GetMapping("/destinations")
    public String showDestinationsPage(Model model) {

        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        return "destinations";
    }

    /**
     * Shows a specific destination's home page.
     *
     * @param destinationId ID of destination to be shown.
     * @param model         adds destination object, name, and images to view.
     * @return displays a specific destination page.
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
     * @return displays new-destination page.
     */
    @GetMapping("/newDestination")
    public String ShowNewDestinationPage(Model model) {
        model.addAttribute("newDestination", new Destination());
        return "new-destination";
    }

    /**
     * Displays a destination that was selected from the destination results page.
     *
     * @param destinationId destination ID used to get a destination from the database.
     * @param weather       weather selected from destination finder.
     * @param scenery       scenery selected from destination finder.
     * @param model         adds a destination's name, object, and images to view. Also, adds weather and scenery
     *                      selected from the destination finder to view.
     * @return
     * @throws Exception
     */
    @GetMapping("/destination/{destinationId}/{weather}/{scenery}")
    public String viewDestinationFromDestinationResultsPage(
            @PathVariable("destinationId") long destinationId,
            @PathVariable("weather") String weather,
            @PathVariable("scenery") String scenery, Model model)
            throws Exception {

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
     * Shows destination finder page.
     *
     * @param model adds empty questionnaire to view.
     * @return displays destination-finder page.
     */
    @GetMapping("/destinationFinder")
    public String showDestinationFinderPage(Model model) {
        model.addAttribute("questionnaire", new Questionnaire());

        List<String> sceneryList = destinationService.getAvailableSceneries();
        model.addAttribute("sceneryList", sceneryList);

        List<String> weatherList = destinationService.getAvailableWeather();
        model.addAttribute("weatherList", weatherList);

        return "destination-finder";
    }

    /**
     * Returns to the destination finder results page.
     *
     * @param weather weather originally selected.
     * @param scenery scenery originally selected.
     * @param model   adds a list of destinations with matching weather and scenery to view.
     *                Also, returns a list of destinations with matching weather.
     * @return
     */
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

    /**
     * Displays edit destination page.
     *
     * @param destinationId destination ID used to get a destination from the database.
     * @param model         adds destination object and ID to view.
     * @return displays edit destination page.
     * @throws Exception
     */
    @GetMapping("/editDestination/{destinationId}")
    public String editDestination(@PathVariable("destinationId") Long destinationId, Model model) throws Exception {
        Destination dbDestination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destination", dbDestination);
        model.addAttribute("destinationId", dbDestination.getId());
        return "edit-destination";
    }

    /**
     * Displays add destination page.
     *
     * @param tripId trip ID used to get a trip from database.
     * @param model  adds a list of destinations and a trip object to model.
     * @return displays add destination page.
     * @throws Exception
     */
    @GetMapping("/destinations/{tripId}")
    public String showDestinationsToAddToTrip(@PathVariable("tripId") Long tripId, Model model) throws Exception {

        List<Destination> destinationsList = destinationService.getAllDestinations();
        model.addAttribute("destinationsList", destinationsList);

        Trip trip = tripService.getById(tripId);
        model.addAttribute("trip", trip);

        return "add-destination-page";
    }

    @GetMapping("/addDestination/{destinationId}/{tripId}")
    public String addDestinationToTrip(@ModelAttribute("destinationId") Long destinationId, @ModelAttribute("tripId") Long tripId) throws Exception {
        Destination dbDestination = destinationService.getDestinationById(destinationId);
        Trip dbTrip = tripService.getById(tripId);

        dbTrip.getDestinations().add(dbDestination);

        tripService.saveTrip(dbTrip);

        return "redirect:/trips";
    }

    @GetMapping("/removeDestination/{destinationId}/{tripId}")
    public String removeDestinationFromTrip(@ModelAttribute("destinationId") Long destinationId, @ModelAttribute("tripId") Long tripId) throws Exception {
        Destination dbDestination = destinationService.getDestinationById(destinationId);
        Trip dbTrip = tripService.getById(tripId);

        dbTrip.getDestinations().remove(dbDestination);

        tripService.saveTrip(dbTrip);

        return "redirect:/trips";
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
     * Shows destination finder results page.
     *
     * @param questionnaire answers from destination finder page.
     * @param model         adds a list of destinations with matching weather and scenery.
     * @return displays destination finder results page.
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
