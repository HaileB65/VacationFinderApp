package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.*;
import Capstone.Project.VacationFinderApp.models.skyscanner.Flight;
import Capstone.Project.VacationFinderApp.models.skyscanner.carrierresponse.CarrierResponse;
import Capstone.Project.VacationFinderApp.models.skyscanner.flightsearch.QueryLeg;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.FlightId;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerItinerary;
import Capstone.Project.VacationFinderApp.models.skyscanner.skyscannerresponse.SkyscannerResponse;
import Capstone.Project.VacationFinderApp.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.Map;
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

    @Autowired
    WeatherAPIService weatherAPI;

    @Autowired
    SkyscannerAPIService skyscannerAPIService;


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
    public String selectTrip(Model model) {
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
    public String userTripPage(@PathVariable("tripName") String tripName, @AuthenticationPrincipal User currentUser, @ModelAttribute("queryLeg") QueryLeg userQueryLeg1, Model model) throws Exception {

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
            Checklist checklist = Checklist.builder()
                    .id(1L)
                    .name("")
                    .item1("")
                    .item2("")
                    .item3("")
                    .item4("")
                    .item5("")
                    .item6("")
                    .item7("")
                    .item8("")
                    .item9("")
                    .item10("")
                    .checkbox1("false")
                    .build();
            model.addAttribute("checklist", checklist);
        }

        //TODO
        // Retrieve trip weather forecast from database.
//        model.addAttribute("weatherURL", weatherAPI.postNewForecast(trip.getCity(), trip.getCountry()));

        QueryLeg queryLeg = new QueryLeg();
        model.addAttribute("queryLeg", queryLeg);

        if (userQueryLeg1.getOriginPlaceId() != null) {

            boolean noFlightsFound = true;

            SkyscannerResponse createSearchResponse;

            try {
                createSearchResponse = skyscannerAPIService.createNewSearch(userQueryLeg1.getOriginPlaceId().getIata(), userQueryLeg1.getDestinationPlaceId().getIata());
            } catch (Exception ex) {
                model.addAttribute("iataCodeError", "Iata code not found");
                return "trip-page";
            }

            List<FlightId> cheapestFlightsList = createSearchResponse.getContent().getSortingOptions().getCheapest();
            ArrayList<FlightId> topSixCheapestFlightIds = new ArrayList<>();

            if (!cheapestFlightsList.isEmpty()) {

                noFlightsFound = false;

                for (int i = 0; i < 6; i++) {
                    topSixCheapestFlightIds.add(cheapestFlightsList.get(i));
                }

                Map<String, SkyscannerItinerary> itineraryHashMap = createSearchResponse.getContent().getResults().getItineraries();
                ArrayList<SkyscannerItinerary> valueList = new ArrayList<>(itineraryHashMap.values());
                ArrayList<String> keyList = new ArrayList<>(itineraryHashMap.keySet());

                ArrayList<Flight> topSixCheapestFlights = new ArrayList<>();

                CarrierResponse carriers = skyscannerAPIService.getIataCodes();

                for (FlightId flightId : topSixCheapestFlightIds) {

                    Flight flight = new Flight();
                    SkyscannerItinerary itinerary = valueList.get(keyList.indexOf(flightId.getItineraryId()));

                    String carrierId = flightId.getItineraryId().substring(17, 23);

                    String carrierName = carriers.getCarriers().get(carrierId).getName();

                    flight.setCarrier(carrierName);
                    flight.setPrice(itinerary.getPricingOptions().get(0).getPrice().getAmount());
                    flight.setDeepLink(itinerary.getPricingOptions().get(0).getItems().get(0).getDeepLink());

                    topSixCheapestFlights.add(flight);

                }

                System.out.println(topSixCheapestFlights);
                model.addAttribute("topSixCheapestFlights", topSixCheapestFlights);

            }

            model.addAttribute("noFlightsFound", noFlightsFound);

        }

        return "trip-page";
    }

    @PostMapping("/searchFlightPrices")
    public SkyscannerResponse searchFlightPrices(@ModelAttribute("queryLeg") QueryLeg queryLeg) throws JsonProcessingException {

        SkyscannerResponse createSearchResponse = skyscannerAPIService.createNewSearch(queryLeg.getOriginPlaceId().getIata(), queryLeg.getDestinationPlaceId().getIata());

        return createSearchResponse;
    }

    @GetMapping("/newTrip")
    public String createTrip(Model model) {

        model.addAttribute("trip", new Trip());

        return "new-trip";
    }

    @GetMapping("/trips")
    public String showTripsPage(Model model) {

        List<Trip> tripsTable = tripService.getAllTrips();

        model.addAttribute("trips", tripsTable);

        return "trips";
    }

    @GetMapping("/trips/{tripName}")
    public String editTripPage(@PathVariable("tripName") String tripName, Model model) throws Exception {

        Trip trip = tripService.getByName(tripName);
        model.addAttribute("trip", trip);

        model.addAttribute("itineraryId", trip.itinerary.getId());

        model.addAttribute("destinations", trip.destinations);
        return "edit-trip-page";
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

    @PostMapping("/joinTrip/{destination}")
    public String joinTrip(@AuthenticationPrincipal User currentUser, @PathVariable("destination") String destination) throws Exception {

        Trip dbTrip = tripService.getByName(destination);

        User user = userService.getUserById(currentUser.getId());
        user.getTrips().add(dbTrip);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @PostMapping("/saveTrip")
    public String saveTrip(@ModelAttribute("trip") Trip newTrip) {

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

