package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Checklist;
import Capstone.Project.VacationFinder.models.Trip;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.ChecklistService;
import Capstone.Project.VacationFinder.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChecklistController {

    @Autowired
    ChecklistService checklistService;

    @Autowired
    TripService tripService;


    @GetMapping("/createChecklist")
    public String createNewChecklist(Model model) {
        model.addAttribute("newChecklist", new Checklist());
        return "create-checklist";
    }

    @GetMapping("/addChecklist")
    public String showAddChecklistPage(@ModelAttribute("currentTrip") Trip trip, Model model) {
        model.addAttribute("checklist", new Checklist());
        model.addAttribute("currentTrip");
        return "add-checklist";
    }

    @PostMapping("/saveChecklist")
    public String saveChecklist(@ModelAttribute("newChecklist") Checklist newChecklist) {
        checklistService.createNewChecklist(newChecklist);
        return "redirect:/home";
    }

    @PostMapping("/addChecklistToTrip")
    public String showAddChecklistToTripPage(@ModelAttribute("checklist") Checklist checklist, @ModelAttribute("currentTrip") Trip trip, @AuthenticationPrincipal User currentUser, Model model) throws Exception {

        checklistService.createNewChecklist(checklist);

        //TODO add checklist to current trip being created

        trip.setChecklist(checklist);
        tripService.saveTrip(trip);

        return "checklist-added";
    }

}
