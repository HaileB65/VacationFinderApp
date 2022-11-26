package Capstone.Project.NewMVCProject.controllers;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<User> usersTable = userService.getAllUsers();
        model.addAttribute("usersTable", usersTable);

        return "home";
    }

}
