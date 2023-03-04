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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
     */
    @GetMapping("/newTrip")
    public String createTrip(Model model){
        model.addAttribute("destination", new Destination());
        return "new-trip";
    }

    /**
     * Shows a trip's home page.
     *
     * @param tripName    ID of trip to be pulled from database.
     * @param currentUser current user logged in.
     * @param model       adds itineraryId, checklistId, and destinations of trip to view. Adds trip to view.
     * @return displays trip-home-page.
     * @throws Exception
     */
    @GetMapping("/trip/{tripName}")
    public String showTripHomePage(@PathVariable("tripName") String tripName, @AuthenticationPrincipal User currentUser, Model model) throws Exception {

        Trip trip = tripService.getByName(tripName);
        model.addAttribute("trip", trip);

        model.addAttribute("itineraryId", trip.itinerary.getId());

        boolean hasChecklist = false;

        User user = userService.getUserById(currentUser.getId());
        Set<Checklist> userChecklists = user.getChecklists();
        for (Checklist checklist : userChecklists) {
            if (checklist.getName().equalsIgnoreCase(tripName + " Checklist")) {
                Checklist dbChecklist = checklistService.getChecklistById(checklist.getId());
                model.addAttribute("checklist", dbChecklist);
                model.addAttribute("checklistId", dbChecklist.getId());
                hasChecklist = true;
            }
        }

        if (!hasChecklist) {
            Checklist checklist = new Checklist();
            model.addAttribute("checklist", checklist);
        }

        return "trip-home-page";
    }

    @GetMapping("/newTemplate")
    public String createTemplate(Model model) throws Exception {

        model.addAttribute("trip", new Trip());

        return "new-template";
    }

    @GetMapping("/templates")
    public String showTemplatesPage(Model model) throws Exception {

        List<Trip> tripsTable = tripService.getAllTrips();

        model.addAttribute("templates", tripsTable);

        return "templates";
    }

    @GetMapping("/template/{tripName}")
    public String showTemplate(@PathVariable("tripName") String tripName, Model model) throws Exception {

        Trip trip = tripService.getByName(tripName);
        model.addAttribute("trip", trip);

        model.addAttribute("itineraryId", trip.itinerary.getId());

        model.addAttribute("destinations", trip.destinations);

        return "template-page";
    }

    /**
     * Saves a new trip.
     *
     * @param destination destination selected for trip.
     * @param currentUser current user logged in.
     * @return redirects to myTrips page.
     * @throws Exception
     */
    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("destination") Destination destination, @AuthenticationPrincipal User currentUser) throws Exception {

        Destination des = destinationService.getByName(destination.getName());
        Trip trip = tripService.getByName(des.getName() + " Trip");

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().add(trip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @PostMapping("/saveTemplate")
    public String saveTemplate(@ModelAttribute("trip") Trip newTrip) throws Exception {

        newTrip.setItinerary(new Itinerary());

        tripService.saveTrip(newTrip);

        return "redirect:/templates";
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

        Set<Checklist> userChecklists = user.getChecklists();
        for (Checklist checklist : userChecklists) {
            if (checklist.getName().equalsIgnoreCase(trip.getName() + " Checklist")) {
                Checklist checklistFromDB = checklistService.getChecklistById(checklist.getId());
                checklistService.deleteChecklist(checklistFromDB);
            }
        }

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

