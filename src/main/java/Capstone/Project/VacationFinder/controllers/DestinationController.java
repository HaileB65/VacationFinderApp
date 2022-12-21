package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.Questionnaire;
import Capstone.Project.VacationFinder.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @GetMapping("/destinations")
    public String showDestinationsPage(Model model) {
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        return "destinations";
    }

    @GetMapping("/theBahamas")
    public String showBahamasPage(Model model) {
        return "the-bahamas";
    }


    @GetMapping("/destination/{destinationId}")
    public String showDestinationPage(@PathVariable("destinationId")long destinationId, Model model) throws Exception {

        Destination destination = destinationService.getDestinationById(destinationId);
        model.addAttribute("destinationName", destination.getName());
        model.addAttribute("destination", destination);

        model.addAttribute("image1", destination.getImage1());
        model.addAttribute("image2", destination.getImage2());
        return "destination";
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
        List<Destination> searchedDestinations = destinationService.getByWeather(questionnaire.getWeather());
        model.addAttribute("searchedDestinations", searchedDestinations);

        model.addAttribute("questionnaire", questionnaire);

        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        return "destination-finder-results";
    }

    @GetMapping("/createNewTrip")
    public String showCreateNewTripPage(Model model) {
        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "create-new-trip";
    }

}
