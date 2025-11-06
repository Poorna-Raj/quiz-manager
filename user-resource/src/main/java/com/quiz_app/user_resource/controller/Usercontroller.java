package com.quiz_app.user_resource.controller;

import com.quiz_app.user_resource.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz_app.user_resource.data.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class Usercontroller {

    @Autowired
    private UserService userService;

    //  Create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation errors and return as a response
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.badRequest().body("Validation failed: " + errors.toString());
        }

        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok("User created successfully: " + savedUser.getUsername());
    }

    //  Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //  Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    //  Update user
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Collect all validation errors and return as a response
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                    errors.append(error.getDefaultMessage()).append("; ")
            );
            return ResponseEntity.badRequest().body("Validation failed: " + errors.toString());
        }

        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok("User updated successfully: " + updatedUser.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //  Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}