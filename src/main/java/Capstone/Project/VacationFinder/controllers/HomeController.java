package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Destination;
import Capstone.Project.VacationFinder.models.Questionnaire;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.DestinationService;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    DestinationService destinationService;

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<User> usersTable = userService.getAllUsers();
        model.addAttribute("usersTable", usersTable);

        Questionnaire questionnaire = new Questionnaire();
        model.addAttribute("questionnaire", questionnaire);

        return "home";
    }

    @PostMapping("/resultsPage")
    public String showQuestionnaireResultsPage(@ModelAttribute("questionnaire") Questionnaire questionnaire, Model model) {
        model.addAttribute("questionnaire", questionnaire);

        List<Destination> destinationsTable = destinationService.getDestinationBySceneryAndMinimumBudgetLessThan(questionnaire.getFavoriteScenery(), questionnaire.getBudget());
        model.addAttribute("destinationsTable", destinationsTable);

        return "results-page";
    }


}
