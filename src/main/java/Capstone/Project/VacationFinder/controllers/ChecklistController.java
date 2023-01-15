package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Checklist;
import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.ChecklistService;
import Capstone.Project.VacationFinder.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String showAddChecklistPage(Model model) {
        model.addAttribute("checklist", new Checklist());
        return "add-checklist";
    }

    @GetMapping("/checklist/{checklistId}")
    public String editChecklistPage(@PathVariable("checklistId") long checklistId, Model model) throws Exception {

        Checklist checklist = checklistService.getChecklistById(checklistId);
        model.addAttribute("checklist", checklist);

        return "edit-checklist";
    }

    @PostMapping("/editChecklist")
    public String editChecklist(@ModelAttribute("checklist") Checklist checklist) {
        checklistService.editChecklist(checklist);
        return "redirect:/myTrips";
    }

    @PostMapping("/saveChecklist")
    public String saveChecklist(@ModelAttribute("newChecklist") Checklist newChecklist) {
        checklistService.createNewChecklist(newChecklist);
        return "redirect:/home";
    }

    /**
     * Edits checklist based on user input from form.
     * @param checklist newly updated checklist
     * @param checklistId used to pull existing checklist from database.
     * @return redirects to my trip page.
     * @throws Exception
     */
    @PostMapping("/editChecklist/{checklistId}")
    public String editChecklist(@ModelAttribute("checklist") Checklist checklist, @PathVariable(name = "checklistId") Long checklistId) throws Exception {
        Checklist che = checklistService.getChecklistById(checklistId);

        che.setChecklistItems(checklist.getChecklistItems());

        checklistService.saveChecklist(che);
        return "redirect:/myTrips";
    }

}
