package Capstone.Project.VacationFinder.controllers;

import Capstone.Project.VacationFinder.models.User;
import Capstone.Project.VacationFinder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RestUserController {

    @Autowired
    UserService userService;

    /**
     * Gets all users from database.
     *
     * @return returns all users found.
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Creates new user.
     *
     * @param user user to be saved.
     * @return returns new user info.
     */
    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        try {
            User newUser = userService.createNewUser(user);
            return ResponseEntity.created(newUser.getLocationURI()).body(newUser);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
