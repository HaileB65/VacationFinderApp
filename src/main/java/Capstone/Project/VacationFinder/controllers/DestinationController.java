package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.*;
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
import java.util.*;

@Controller
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @GetMapping("/destinations")
    public String showDestinationsPage(@AuthenticationPrincipal User currentUser, Model model) throws Exception {
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        User user = userService.getUserById(currentUser.getId());
        model.addAttribute("savedDestinations", user.savedDestinations);

        return "destinations";
    }

    @GetMapping("/theBahamas")
    public String showBahamasPage(Model model) {
        return "the-bahamas";
    }


    @GetMapping("/destination/{destinationId}")
    public String showDestinationPage(@PathVariable("destinationId") long destinationId, Model model) throws Exception {

        Destination destination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destinationName", destination.getName());
        model.addAttribute("destination", destination);

        ArrayList<String> images = new ArrayList<>();
        images.add(destination.getImage1());
        images.add(destination.getImage2());
        model.addAttribute("images", images);

        return "destination";
    }

    @GetMapping("/addToSavedDestinations/{destinationId}")
    public String addToSavedDestinations(@PathVariable("destinationId") long destinationId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {
        System.out.println("start of add destination method");

        Destination destination = destinationService.getDestinationById(destinationId);

        User user = userService.getUserById(currentUser.getId());
        user.getSavedDestinations().add(destination);
        userService.saveUser(user);

        System.out.println("end of add destination method");

        return "redirect:/destinations";
    }

    @GetMapping("/removeFromSavedDestinations/{destinationId}")
    public String removeFromSavedDestinations(@PathVariable("destinationId") long destinationId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {
        System.out.println("start of add destination method");

        Destination destination = destinationService.getDestinationById(destinationId);

        User user = userService.getUserById(currentUser.getId());
        user.getSavedDestinations().remove(destination);
        userService.saveUser(user);

        System.out.println("end of add destination method");

        return "redirect:/destinations";
    }

    @GetMapping("/addDestinationToTrip/{destinationId}/{tripId}")
    public String addDestinationToTrip(@PathVariable("destinationId") long destinationId, @PathVariable("tripId") long tripId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {
        System.out.println("start of add destination method");

        Trip trip = tripService.getTripById(tripId);
        trip.getDestinations().clear();

        Destination destination = destinationService.getDestinationById(destinationId);
        trip.getDestinations().add(destination);

        tripService.saveTrip(trip);

        System.out.println("end of add destination method");

        return "redirect:/myTrips";
    }

    public Timestamp getTimestamp() {
        Calendar cal = Calendar.getInstance();
        Date result = cal.getTime();
        return new Timestamp(result.getTime());
    }

    @PostMapping("/addDestination")
    public String addDestinationToTrip(Destination destination) {
        destinationService.addToTrip(destination);
        return "destination-added";
    }

    @GetMapping("/switzerland")
    public String showSwitzerlandPage(Model model) {
        return "switzerland";
    }

    @GetMapping("/newDestination")
    public String createNewDestination(Model model) {
        model.addAttribute("newDestination", new Destination());
        return "new-destination";
    }

    @PostMapping("/saveDestination")
    public String saveDestination(@ModelAttribute("newDestination") Destination destination) {
        destinationService.createNewDestination(destination);
        return "redirect:/destinations";
    }

    @PostMapping("/destinationFinderResults")
    public String showQuestionnaireResultsPage(@ModelAttribute("questionnaire") Questionnaire questionnaire, Model model) {
        model.addAttribute("questionnaire", questionnaire);

        List<Destination> searchedDestinations = destinationService.getByWeather(questionnaire.getWeather());
        model.addAttribute("searchedDestinations", searchedDestinations);

        List<Destination> remainingDestinations = destinationService.getAllDestinations();
        for(Destination destination:searchedDestinations){
            remainingDestinations.remove(destination);
        }
        model.addAttribute("remainingDestinations", remainingDestinations);

        return "destination-finder-results";
    }

    @GetMapping("/destinationFinder")
    public String showCreateNewTripPage(Model model) {
        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "destination-finder";
    }

}
