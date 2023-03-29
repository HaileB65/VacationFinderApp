package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.Checklist;
import Capstone.Project.VacationFinderApp.models.User;
import Capstone.Project.VacationFinderApp.services.ChecklistService;
import Capstone.Project.VacationFinderApp.services.UserService;
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
        checklist.setItem1("1");
        checklist.setItem2("2");
        checklist.setItem3("3");
        checklist.setItem4("4");
        checklist.setItem5("5");
        checklist.setItem6("6");
        checklist.setItem7("7");
        checklist.setItem8("8");
        checklist.setItem9("9");
        checklist.setItem10("10");

        model.addAttribute("checklist", checklist);
        model.addAttribute("tripName", tripName);
        return "new-checklist";
    }

    @GetMapping("/editChecklist/{checklistId}")
    public String editChecklistPageByID(@PathVariable("checklistId") Long checklistId, Model model) throws Exception {
        Checklist dbChecklist = checklistService.getChecklistById(checklistId);
        model.addAttribute("checklist", dbChecklist);
        model.addAttribute("checklistId", dbChecklist.getId());
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
        checklist.setName(tripName + " Checklist");

        User user = userService.getUserById(currentUser.getId());
        user.getChecklists().add(checklist);
        userService.saveUser(user);

        return "redirect:/myTrips";
    }

    @PostMapping("/saveChecklist/{checklistId}")
    public String saveChecklistById(@ModelAttribute("checklist") Checklist viewChecklist, @PathVariable(name = "checklistId") Long checklistId) throws Exception {

        Checklist dbChecklist = checklistService.getChecklistById(checklistId);
        dbChecklist.setItem1(viewChecklist.getItem1());
        dbChecklist.setItem2(viewChecklist.getItem2());
        dbChecklist.setItem3(viewChecklist.getItem3());
        dbChecklist.setItem4(viewChecklist.getItem4());
        dbChecklist.setItem5(viewChecklist.getItem5());
        dbChecklist.setItem6(viewChecklist.getItem6());
        dbChecklist.setItem7(viewChecklist.getItem7());
        dbChecklist.setItem8(viewChecklist.getItem8());
        dbChecklist.setItem9(viewChecklist.getItem9());
        dbChecklist.setItem10(viewChecklist.getItem10());

        checklistService.saveChecklist(dbChecklist);
        return "redirect:/myTrips";

    }

}
