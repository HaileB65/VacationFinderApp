package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
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

    @GetMapping("/newTrip")
    public String createTrip(Model model) throws Exception {
        Trip newTrip = new Trip();
        model.addAttribute("newTrip", newTrip);

        Destination destination = new Destination();
        model.addAttribute("destination", destination);
        return "new-trip";
    }

    @GetMapping("/trip/{tripId}")
    public String showTrip(@PathVariable("tripId") long tripId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {

        Trip trip = tripService.getById(tripId);
        model.addAttribute("tripName", trip.getName());
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

        User user = userService.getUserById(currentUser.getId());
        model.addAttribute("savedDestinations", user.savedDestinations);

        return "trip-home-page";
    }

    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("newTrip") Trip newTrip, @ModelAttribute("destination") Destination destination, @AuthenticationPrincipal User currentUser) throws Exception {
        newTrip.setDestinations(new HashSet<>());

        Itinerary newItinerary = new Itinerary();
        itineraryService.saveItinerary(newItinerary);
        newTrip.setItinerary(newItinerary);

        Checklist newChecklist = new Checklist();
        checklistService.saveChecklist(newChecklist);
        newTrip.setChecklist(newChecklist);
        tripService.createNewTrip(newTrip);

        Trip trip = tripService.getById(newTrip.getId());
        Destination des = destinationService.getByName(destination.getName());
        trip.getDestinations().add(des);
        trip.setName(des.getName());
        tripService.saveTrip(trip);

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().add(trip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @GetMapping("/deleteTrip/{tripId}")
    public String deleteTrip(@PathVariable("tripId") long tripId, @AuthenticationPrincipal User currentUser, Model model) throws Exception {
        Trip trip = tripService.getById(tripId);
        User user = userService.getUserById(currentUser.getId());

        trip.getUsers().remove(user);

        Itinerary itineraryFromDB = itineraryService.getItineraryById(trip.getItinerary().getId());
        itineraryService.deleteItinerary(itineraryFromDB);
        Checklist checklistFromDB = checklistService.getChecklistById(trip.getChecklist().getId());
        checklistService.deleteChecklist(checklistFromDB);

        List<Destination> destinations = trip.getDestinations().stream().toList();
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

