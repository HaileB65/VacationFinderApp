package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.Questionnaire;
import Capstone.Project.VacationFinder.services.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DestinationController {

    @Autowired
    DestinationService destinationService;

    @GetMapping("/destinations")
    public String showDestinationsPage(Model model){
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable", destinationsTable);

        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "destinations";
    }

    @GetMapping("/vacationSpotFinder")
    public String showVacationSpotFinderPage(Model model){
        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "vacation-spot-finder";
    }

    @GetMapping("/newDestination")
    public String createNewDestination(Model model){
        model.addAttribute("newDestination", new Destination());
        return "new-destination";
    }

    @PostMapping("/saveDestination")
    public String saveDestination(@ModelAttribute("newDestination")Destination destination){
        destinationService.createNewDestination(destination);
        return "redirect:/destinations";
    }

    @PostMapping("/resultsPage")
    public String showQuestionnaireResultsPage(@ModelAttribute("questionnaire") Questionnaire questionnaire, Model model){
        model.addAttribute("questionnaire", questionnaire);

//        List<Destination> destinationsTable = destinationService.getDestinationWhereMinimumBudgetGreaterThan(questionnaire.getHolidayBudget());
        List<Destination> destinationsTable = destinationService.getAllDestinations();
        model.addAttribute("destinationsTable",destinationsTable);

        return "results-page";
    }

}
