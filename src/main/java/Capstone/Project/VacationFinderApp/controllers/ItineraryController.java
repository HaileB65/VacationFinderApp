package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.Itinerary;
import Capstone.Project.VacationFinderApp.services.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItineraryController {

    @Autowired
    ItineraryService itineraryService;

    /**
     * Displays create-itinerary page.
     *
     * @param model adds an empty itinerary object to view.
     * @return displays create-itinerary page.
     */
    @GetMapping("/createItinerary")
    public String createNewItinerary(Model model) {
        model.addAttribute("newItinerary", new Itinerary());
        return "create-itinerary";
    }

    /**
     * Edits an existing itinerary.
     *
     * @param itineraryId ID of itinerary to be edited.
     * @param model       displays itinerary from DB that was found using itineraryId param.
     * @return displays edit-itinerary page.
     * @throws Exception
     */
    @GetMapping("/editItinerary/{itineraryId}")
    public String editItineraryFromTripHomePage(@PathVariable("itineraryId") long itineraryId, Model model) throws Exception {
        Itinerary itinerary = itineraryService.getItineraryById(itineraryId);
        model.addAttribute("itinerary", itinerary);
        return "edit-itinerary-page";
    }

    /**
     * Saves a newly created itinerary.
     *
     * @param newItinerary itinerary to be saved.
     * @return redirects to home page.
     */
    @PostMapping("/saveItinerary")
    public String saveItinerary(@ModelAttribute("newItinerary") Itinerary newItinerary) {
        itineraryService.createNewItinerary(newItinerary);
        return "redirect:/home";
    }

    /**
     * Edits an existing itinerary.
     *
     * @param viewItinerary edited itinerary.
     * @param itineraryId   ID of existing itinerary to be pulled from database.
     * @return redirects to myTrips page.
     * @throws Exception
     */
    @PostMapping("/editItineraryFromUserTripPage/{itineraryId}")
    public String editItineraryFromUserTripPage(@ModelAttribute("itinerary") Itinerary viewItinerary, @PathVariable(name = "itineraryId") Long itineraryId) throws Exception {
        Itinerary dbItinerary = itineraryService.getItineraryById(itineraryId);

        dbItinerary.setLocation1(viewItinerary.getLocation1());
        dbItinerary.setLocation2(viewItinerary.getLocation2());
        dbItinerary.setLocation3(viewItinerary.getLocation3());
        dbItinerary.setLocation4(viewItinerary.getLocation4());
        dbItinerary.setLocation5(viewItinerary.getLocation5());
        dbItinerary.setMeal1(viewItinerary.getMeal1());
        dbItinerary.setMeal2(viewItinerary.getMeal2());
        dbItinerary.setMeal3(viewItinerary.getMeal3());
        dbItinerary.setMeal4(viewItinerary.getMeal4());
        dbItinerary.setMeal5(viewItinerary.getMeal5());
        dbItinerary.setLeisure1(viewItinerary.getLeisure1());
        dbItinerary.setLeisure2(viewItinerary.getLeisure2());
        dbItinerary.setLeisure3(viewItinerary.getLeisure3());
        dbItinerary.setLeisure4(viewItinerary.getLeisure4());
        dbItinerary.setLeisure5(viewItinerary.getLeisure5());
        dbItinerary.setTransport1(viewItinerary.getTransport1());
        dbItinerary.setTransport2(viewItinerary.getTransport2());
        dbItinerary.setTransport3(viewItinerary.getTransport3());
        dbItinerary.setTransport4(viewItinerary.getTransport4());
        dbItinerary.setTransport5(viewItinerary.getTransport5());

        itineraryService.saveItinerary(dbItinerary);
        return "redirect:/myTrips";
    }

    @PostMapping("/editItineraryFromTripHomePage/{itineraryId}")
    public String editItineraryFromTripHomePage(@ModelAttribute("itinerary") Itinerary viewItinerary, @PathVariable(name = "itineraryId") Long itineraryId) throws Exception {
        Itinerary dbItinerary = itineraryService.getItineraryById(itineraryId);

        dbItinerary.setLocation1(viewItinerary.getLocation1());
        dbItinerary.setLocation2(viewItinerary.getLocation2());
        dbItinerary.setLocation3(viewItinerary.getLocation3());
        dbItinerary.setLocation4(viewItinerary.getLocation4());
        dbItinerary.setLocation5(viewItinerary.getLocation5());
        dbItinerary.setMeal1(viewItinerary.getMeal1());
        dbItinerary.setMeal2(viewItinerary.getMeal2());
        dbItinerary.setMeal3(viewItinerary.getMeal3());
        dbItinerary.setMeal4(viewItinerary.getMeal4());
        dbItinerary.setMeal5(viewItinerary.getMeal5());
        dbItinerary.setLeisure1(viewItinerary.getLeisure1());
        dbItinerary.setLeisure2(viewItinerary.getLeisure2());
        dbItinerary.setLeisure3(viewItinerary.getLeisure3());
        dbItinerary.setLeisure4(viewItinerary.getLeisure4());
        dbItinerary.setLeisure5(viewItinerary.getLeisure5());
        dbItinerary.setTransport1(viewItinerary.getTransport1());
        dbItinerary.setTransport2(viewItinerary.getTransport2());
        dbItinerary.setTransport3(viewItinerary.getTransport3());
        dbItinerary.setTransport4(viewItinerary.getTransport4());
        dbItinerary.setTransport5(viewItinerary.getTransport5());

        itineraryService.saveItinerary(dbItinerary);
        return "redirect:/trips";
    }

}
