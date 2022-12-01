package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Scenery;
import Capstone.Project.VacationFinder.models.User;
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

    @GetMapping("/")
    public String showHomePage(Model model){
        Scenery scenery = new Scenery();
        model.addAttribute("scenery", scenery);

        return "home";
    }

    @GetMapping("/filled-out-questionnaire")
    public String showFilledOutQuestionnairePage(@ModelAttribute("scenery") Scenery scenery, Model model){
        System.out.println("scenery = " + scenery.getSelected());
        model.addAttribute("scenery", scenery);

        return "filled-out-questionnaire";
    }


}
