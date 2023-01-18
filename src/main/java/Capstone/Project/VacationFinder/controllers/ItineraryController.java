package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Itinerary;
import Capstone.Project.VacationFinder.services.ItineraryService;
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

    @GetMapping("/createItinerary")
    public String createNewItinerary(Model model) {
        model.addAttribute("newItinerary", new Itinerary());
        return "create-itinerary";
    }

    @PostMapping("/saveItinerary")
    public String saveItinerary(@ModelAttribute("newItinerary") Itinerary newItinerary) {
        itineraryService.createNewItinerary(newItinerary);
        return "redirect:/home";
    }

    @PostMapping("/editItinerary/{itineraryId}")
    public String editItinerary(@ModelAttribute("itinerary") Itinerary itinerary, @PathVariable(name = "itineraryId") Long itineraryId) throws Exception {
        Itinerary itn = itineraryService.getItineraryById(itineraryId);

        itn.setItem1(itinerary.getItem1());
        itn.setItem2(itinerary.getItem2());
        itn.setItem3(itinerary.getItem3());

        itineraryService.saveItinerary(itn);
        return "redirect:/myTrips";
    }

}
