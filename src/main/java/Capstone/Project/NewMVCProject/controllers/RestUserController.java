package Capstone.Project.NewMVCProject.controllers;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.models.apiUser;
import Capstone.Project.NewMVCProject.services.CodingNomadsAPIService;
import Capstone.Project.NewMVCProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {

    @Autowired
    UserService userService;

    @Autowired
    CodingNomadsAPIService codingNomadsAPIService;


    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        try{
            User newUser = userService.createNewUser(user);
            return ResponseEntity.created(newUser.getLocationURI()).body(newUser);
        }catch (IllegalStateException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @GetMapping("/CNAPI")
    public List<apiUser> getCNAPIUsers() {
        return codingNomadsAPIService.getAllUsers();
    }

    }
