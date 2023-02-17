package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.Checklist;
import Capstone.Project.VacationFinderApp.models.User;
import Capstone.Project.VacationFinderApp.services.ChecklistService;
import Capstone.Project.VacationFinderApp.services.UserService;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Autowired
    UserService userService;

    /**
     * Displays new checklist page with a prefilled checklist template.
     *
     * @param model adds prefilled checklist to view.
     * @return displays create-checklist page.
     */
    @GetMapping("/newChecklist/{tripName}")
    public String newChecklistPage(@PathVariable("tripName") String tripName, Model model) {
        Checklist checklist = new Checklist();
        checklist.setName(tripName + " Checklist");
        checklist.setChecklistItems(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        model.addAttribute("checklist", checklist);
        return "new-checklist";
    }

    /**
     * Edits an existing checklist.
     *
     * @param checklistId ID of checklist to be edited.
     * @param model       displays checklist from DB that was found using checklistId param.
     * @return displays edit-checklist page.
     * @throws Exception
     */
    @GetMapping("/editChecklist/{checklistId}")
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
     * Saves a checklist.
     *
     * @param checklist checklist to be saved.
     * @return redirects to my trips page.
     */
    @PostMapping("/saveChecklist")
    public String saveChecklist(@ModelAttribute("checklist") Checklist checklist) {
        checklistService.saveChecklist(checklist);
        return "redirect:/myTrips";
    }

    /**
     * Creates a new checklist and saves it to database.
     *
     * @param checklist newly created checklist.
     * @return redirects to my trips page.
     */
    @PostMapping("/createChecklist")
    public String createChecklist(@ModelAttribute("checklist") Checklist checklist, @AuthenticationPrincipal User currentUser) {
        currentUser.getChecklists().add(checklist);
        userService.saveUser(currentUser);
        return "redirect:/myTrips";
    }

    /**
     * Edits checklist based on user input from form.
     *
     * @param viewChecklist   newly updated checklist.
     * @param checklistId used to pull existing checklist from database.
     * @return redirects to my trips page.
     * @throws Exception
     */
    @PostMapping("/editChecklist/{checklistId}")
    public String editChecklist(@ModelAttribute("checklist") Checklist viewChecklist, @PathVariable(name = "checklistId") Long checklistId) throws Exception {
        Checklist dbChecklist = checklistService.getChecklistById(checklistId);

        dbChecklist.setChecklistItems(viewChecklist.getChecklistItems());

        checklistService.saveChecklist(dbChecklist);
        return "redirect:/myTrips";
    }

}
