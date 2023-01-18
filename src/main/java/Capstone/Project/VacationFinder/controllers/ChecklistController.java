package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.Checklist;
import Capstone.Project.VacationFinder.services.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class ChecklistController {

    @Autowired
    ChecklistService checklistService;

    /**
     * Creates a new checklist.
     *
     * @param model adds empty checklist to view.
     * @return displays create-checklist page.
     */
    @GetMapping("/createChecklist")
    public String createNewChecklist(Model model) {
        model.addAttribute("newChecklist", new Checklist());
        return "create-checklist";
    }

    /**
     * Edits an existing checklist.
     *
     * @param checklistId ID of checklist to be edited.
     * @param model displays checklist from DB that was found using checklistId param.
     * @return displays edit-checklist page.
     * @throws Exception
     */
    @GetMapping("/checklist/{checklistId}")
    public String editChecklistPage(@PathVariable("checklistId") long checklistId, Model model) throws Exception {
        Checklist checklist = checklistService.getChecklistById(checklistId);
        model.addAttribute("checklist", checklist);
        return "edit-checklist";
    }

    /**
     * Saves edited checklist.
     *
     * @param checklist edited checklist.
     * @return redirect to my trips page.
     */
    @PostMapping("/editChecklist")
    public String editChecklist(@ModelAttribute("checklist") Checklist checklist) {
        checklistService.saveChecklist(checklist);
        return "redirect:/myTrips";
    }

    /**
     * Saves newly created checklist.
     *
     * @param newChecklist newly created checklist.
     * @return redirects to my trips page.
     */
    @PostMapping("/saveChecklist")
    public String saveChecklist(@ModelAttribute("newChecklist") Checklist newChecklist) {
        checklistService.createNewChecklist(newChecklist);
        return "redirect:/myTrips";
    }

    /**
     * Edits checklist based on user input from form.
     *
     * @param checklist   newly updated checklist.
     * @param checklistId used to pull existing checklist from database.
     * @return redirects to my trips page.
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
