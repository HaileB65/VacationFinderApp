package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.*;
import Capstone.Project.VacationFinderApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class TripController {

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    ItineraryService itineraryService;

    @Autowired
    ChecklistService checklistService;

    @Autowired
    DestinationService destinationService;


    /**
     * Shows all trips that a user has.
     *
     * @param currentUser current user logged in.
     * @param model       adds user trips to view.
     * @return displays my-trips page.
     * @throws Exception
     */
    @GetMapping("/myTrips")
    public String showMyTripsPage(@AuthenticationPrincipal User currentUser, Model model) throws Exception {
        User user = userService.getUserById(currentUser.getId());

        Set<Trip> userTrips = user.getTrips();

        ArrayList<Trip> trips = new ArrayList<>();

        for (Trip trip : userTrips) {
            trips.add(trip);
        }

        model.addAttribute("trips", trips);

        return "my-trips";
    }

    /**
     * Creates a new trip.
     *
     * @param model adds an empty trip and empty destination to view.
     * @return displays new-trip page.
     * @throws Exception
     */
    @GetMapping("/newTrip")
    public String createTrip(Model model) throws Exception {
        model.addAttribute("newTrip", new Trip());

        model.addAttribute("destination", new Destination());
        return "new-trip";
    }

    /**
     * Shows a trip's home page.
     *
     * @param tripId      ID of trip to be pulled from database.
     * @param currentUser current user logged in.
     * @param model       adds itineraryId, checklistId, and destinations of trip to view. Adds trip to view.
     * @return displays trip-home-page.
     * @throws Exception
     */
    @GetMapping("/trip/{tripId}")
    public String showTripHomePage(@PathVariable("tripId") long tripId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {

        Trip trip = tripService.getById(tripId);
        model.addAttribute("trip", trip);

        if (trip.itinerary == null) {
            Itinerary newItinerary = new Itinerary();
            itineraryService.saveItinerary(newItinerary);
            trip.setItinerary(newItinerary);
            tripService.saveTrip(trip);
        }
        model.addAttribute("itineraryId", trip.itinerary.getId());

        if (trip.checklist == null) {
            Checklist newChecklist = new Checklist();
            checklistService.saveChecklist(newChecklist);
            trip.setChecklist(newChecklist);
            tripService.saveTrip(trip);
        }
        model.addAttribute("checklistId", trip.checklist.getId());


        if (trip.destinations == null) {
            Set<Destination> destinations = new HashSet();
            trip.setDestinations(destinations);
            tripService.saveTrip(trip);
        }
        model.addAttribute("destinations", trip.destinations);

        return "trip-home-page";
    }

    /**
     * Saves a new trip.
     *
     * @param newTrip     new trip to be saved.
     * @param destination destination selected for trip.
     * @param currentUser current user logged in.
     * @return redirects to myTrips page.
     * @throws Exception
     */
    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("newTrip") Trip newTrip, @ModelAttribute("destination") Destination destination, @AuthenticationPrincipal User currentUser) throws Exception {
        newTrip.setDestinations(new HashSet<>());

        Itinerary newItinerary = new Itinerary();
        itineraryService.saveItinerary(newItinerary);
        newTrip.setItinerary(newItinerary);

        Checklist newChecklist = new Checklist();
        newChecklist.getChecklistItems().addAll((Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
        checklistService.saveChecklist(newChecklist);
        newTrip.setChecklist(newChecklist);
        tripService.saveTrip(newTrip);

        Trip trip = tripService.getById(newTrip.getId());
        Destination des = destinationService.getByName(destination.getName());
        trip.getDestinations().add(des);
        trip.setName(des.getName() + " Trip");
        tripService.saveTrip(trip);

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().add(trip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    /**
     * Deletes trip.
     *
     * @param tripId      ID of trip to be pulled from database.
     * @param currentUser current user logged in.
     * @return redirects to myTrips page.
     * @throws Exception
     */
    @GetMapping("/deleteTrip/{tripId}")
    public String deleteTrip(@PathVariable("tripId") long tripId, @AuthenticationPrincipal User currentUser) throws Exception {
        Trip trip = tripService.getById(tripId);
        User user = userService.getUserById(currentUser.getId());

        trip.getUsers().remove(user);

        Itinerary itineraryFromDB = itineraryService.getItineraryById(trip.getItinerary().getId());
        itineraryService.deleteItinerary(itineraryFromDB);
        Checklist checklistFromDB = checklistService.getChecklistById(trip.getChecklist().getId());
        checklistService.deleteChecklist(checklistFromDB);

        List<Destination> destinations = new ArrayList<>();
        for (Destination destination : trip.getDestinations()) {
            destinations.add(destination);
        }
        Destination des = destinations.get(0);
        Destination destinationFromDB = destinationService.getDestinationById(des.getId());
        destinationFromDB.getTrips().remove(trip);
        trip.getDestinations().clear();

        tripService.saveTrip(trip);

        user.getTrips().remove(trip);
        tripService.deleteTrip(trip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }


}

