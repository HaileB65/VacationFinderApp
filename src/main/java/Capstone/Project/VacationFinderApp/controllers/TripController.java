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
     * Displays select destination page and adds a empty
     * destination to view.
     *
     * @param model adds an empty destination to view.
     * @return displays new-trip page.
     */
    @GetMapping("/selectTrip")
    public String selectTrip(Model model){
        model.addAttribute("trip", new Trip());

        List<Trip> tripsList = tripService.getAllTrips();
        model.addAttribute("tripsList", tripsList);

        return "select-trip";
    }

    /**
     * Displays a trip the user has joined and a
     * checklist they can edit.
     *
     * @param tripName    ID of trip to be pulled from database.
     * @param currentUser current user logged in.
     * @param model       adds itineraryId, checklistId, and destinations of trip to view. Adds trip to view.
     * @return displays trip-home-page.
     * @throws Exception
     */
    @GetMapping("/trip/{tripName}")
    public String userTripPage(@PathVariable("tripName") String tripName, @AuthenticationPrincipal User currentUser, Model model) throws Exception {

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

        return "user-trip-page";
    }

    @GetMapping("/newTrip")
    public String createTrip(Model model){

        model.addAttribute("trip", new Trip());

        return "new-trip";
    }

    @GetMapping("/trips")
    public String showTripsPage(Model model){

        List<Trip> tripsTable = tripService.getAllTrips();

        model.addAttribute("trips", tripsTable);

        return "trips";
    }

    @GetMapping("/trips/{tripName}")
    public String tripHomePage(@PathVariable("tripName") String tripName, Model model) throws Exception {

        Trip trip = tripService.getByName(tripName);
        model.addAttribute("trip", trip);

        model.addAttribute("itineraryId", trip.itinerary.getId());

        model.addAttribute("destinations", trip.destinations);

        return "trip-home-page";
    }

    @GetMapping("/dropTrip/{tripId}")
    public String dropTrip(@PathVariable("tripId") Long tripId, @AuthenticationPrincipal User currentUser) throws Exception {

        Trip dbTrip = tripService.getById(tripId);

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().remove(dbTrip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @PostMapping("/addDestination/{tripId}")
    public String addDestination(@PathVariable("tripId") Long tripId, Model model) throws Exception {

        Trip dbTrip = tripService.getById(tripId);
        model.addAttribute("trip", dbTrip);

        return "add-destination-page";
    }


    @GetMapping("/removeDestinationFromTrip/{destinationId}/{tripID}")
    public String removeDestination(@PathVariable("destinationId") Long destinationId, @PathVariable("tripId") Long tripId) throws Exception {
        Trip trip = tripService.getById(tripId);
        Destination destination = destinationService.getDestinationById(destinationId);

        trip.getDestinations().remove(destination);

        tripService.saveTrip(trip);

        return "redirect:/trips";
    }

    /**
     * Adds a user to a trip.
     *
     * @param currentUser current user logged in.
     * @return redirects to myTrips page.
     * @throws Exception
     */
    @PostMapping("/joinTrip")
    public String joinTrip(@ModelAttribute("trip") Trip trip, @AuthenticationPrincipal User currentUser) throws Exception {

        Trip dbTrip = tripService.getByName(trip.getName());

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().add(dbTrip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("trip") Trip newTrip){

        newTrip.setItinerary(new Itinerary());

        tripService.saveTrip(newTrip);

        return "redirect:/trips";
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

