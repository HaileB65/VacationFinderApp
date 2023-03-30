package Capstone.Project.VacationFinderApp.controllers;

import Capstone.Project.VacationFinderApp.models.User;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user2")
public class RyanUserController {

    @GetMapping
    public User getUser(){

        return new User();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") String id){

        return new User();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") String id){

    }

    @PostMapping
    public User createUser(){

        return new User();
    }

    @DeleteMapping
    public void deleteUser(){

    }
}
