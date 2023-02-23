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
//        checklist.setName(tripName + " Checklist");
        checklist.setChecklistItems(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
//        checklistService.saveChecklist(checklist);

        model.addAttribute("checklist", checklist);
//        model.addAttribute("checklistId", checklist.getId());
        model.addAttribute("tripName", tripName);
        return "new-checklist";
    }

//    /**
//     * Edits an existing checklist.
//     *
//     * @param checklistName Name of checklist to be edited.
//     * @param model       displays checklist from DB that was found using checklistName parameter.
//     * @return displays edit-checklist page.
//     * @throws Exception
//     */
//    @GetMapping("/editChecklist/{checklistName}")
//    public String editChecklistPage(@PathVariable("checklistName") String checklistName, Model model) throws Exception {
//        Checklist checklist = checklistService.getChecklistByName(checklistName);
//        model.addAttribute("checklist", checklist);
//        return "edit-checklist";
//    }

    @GetMapping("/editChecklist/{checklistId}")
    public String editChecklistPageByID(@PathVariable("checklistId") Long checklistId, Model model) throws Exception {
        Checklist checklist = checklistService.getChecklistById(checklistId);
        model.addAttribute("checklist", checklist);
        return "edit-checklist";
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
    @PostMapping("/createChecklist/{tripName}")
    public String createChecklist(@ModelAttribute("checklist") Checklist checklist, @PathVariable("tripName") String tripName, @AuthenticationPrincipal User currentUser) throws Exception {
        User user = userService.getUserById(currentUser.getId());

        checklist.setName(tripName + " Checklist");
        checklistService.saveChecklist(checklist);

        user.getChecklists().add(checklist);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

//    /**
//     * Saves checklist based on user input from form.
//     *
//     * @param viewChecklist   newly updated checklist.
//     * @param checklistName used to pull existing checklist from database.
//     * @return redirects to my trips page.
//     * @throws Exception
//     */
//    @PostMapping("/saveChecklist/{checklistName}")
//    public String saveChecklistByName(@ModelAttribute("checklist") Checklist viewChecklist, @PathVariable(name = "checklistName") String checklistName) throws Exception {
//        Checklist dbChecklist = checklistService.getChecklistByName(checklistName);
//
//        dbChecklist.setChecklistItems(viewChecklist.getChecklistItems());
//
//        checklistService.saveChecklist(dbChecklist);
//        return "redirect:/myTrips";
//    }

    @PostMapping("/saveChecklist/{checklistId}")
    public String saveChecklistById(@ModelAttribute("checklist") Checklist viewChecklist, @PathVariable(name = "checklistId") Long checklistId) throws Exception {
        Checklist dbChecklist = checklistService.getChecklistById(checklistId);

        dbChecklist.setChecklistItems(viewChecklist.getChecklistItems());

        checklistService.saveChecklist(dbChecklist);
        return "redirect:/myTrips";
    }

}
