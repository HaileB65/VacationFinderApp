package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.*;
import Capstone.Project.VacationFinder.services.*;
import org.hibernate.annotations.Check;
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

    @GetMapping("/myTrips")
    public String showMyTripsPage(@AuthenticationPrincipal User currentUser, Model model) throws Exception {
        User user = userService.getUserById(currentUser.getId());

        Set<Trip> userTrips = user.getTrips();

        ArrayList<Trip> trips = new ArrayList<>();

        for(Trip trip: userTrips){
            trips.add(trip);
        }

        model.addAttribute("trips", trips);

        return "my-trips";
    }

    @GetMapping("/newTrip")
    public String createTrip(Model model) throws Exception {
        Trip newTrip = new Trip();
        model.addAttribute("newTrip", newTrip);
        return "new-trip";
    }

    @GetMapping("/newTripPlanned")
    public String showTrip(Model model) throws Exception {

        Trip trip = tripService.getTripById(1L);
        model.addAttribute("tripName", trip.getName());
        model.addAttribute("trip", trip);

        model.addAttribute("itineraryId", trip.itinerary.getId());
        model.addAttribute("checklistId", trip.checklist.getId());

        Set<Destination> tripDestinations = trip.destinations;
        ArrayList<Destination> destinations = new ArrayList<>();
        for(Destination destination: tripDestinations){
            destinations.add(destination);
        }

        model.addAttribute("destinations", destinations);


        return "trip-home-page";
    }

    @GetMapping("/styledPage")
    public String getStyledPage(Model model) {
        model.addAttribute("name", "Baeldung Reader");
        return "cssandjs/styled-page";
    }

    @GetMapping("/trip/{tripId}")
    public String showTrip(@PathVariable("tripId") long tripId, Model model) throws Exception {

        Trip trip = tripService.getTripById(tripId);
        model.addAttribute("tripName", trip.getName());
        model.addAttribute("trip", trip);

        if(trip.itinerary == null){
            Itinerary newItinerary = new Itinerary();
            itineraryService.saveItinerary(newItinerary);
            trip.setItinerary(newItinerary);
            tripService.saveTrip(trip);
        }
        model.addAttribute("itineraryId", trip.itinerary.getId());

        if(trip.checklist == null){
            Checklist newChecklist = new Checklist();
            checklistService.saveChecklist(newChecklist);
            trip.setChecklist(newChecklist);
            tripService.saveTrip(trip);
        }
        model.addAttribute("checklistId", trip.checklist.getId());


        if(trip.destinations == null){
            Set<Destination> destinations = new HashSet();
            trip.setDestinations(destinations);
            tripService.saveTrip(trip);
        }
        model.addAttribute("destinations", trip.destinations);


        return "trip-home-page";
    }

    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("newTrip") Trip newTrip, @AuthenticationPrincipal User currentUser) throws Exception {
        tripService.createNewTrip(newTrip);

        Trip trip = tripService.getTripById(newTrip.getId());

        currentUser.getTrips().add(trip);

        userService.saveUser(currentUser);

        return "redirect:/myTrips";
    }


}

