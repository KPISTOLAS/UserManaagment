package com.project.Company.controller;

import com.project.Company.model.Role;
import com.project.Company.model.User;
import com.project.Company.request.UserRequest;
import com.project.Company.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController // This controller handles all things "User" like a true guardian
@RequestMapping("/api/users") // Endpoint for user-related operations
@RequiredArgsConstructor // Because who likes writing constructors manually?
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"}) // Allow frontend(s) to talk to us
public class UserController {

    private final UserService userService; // The real MVP handling business logic

    @GetMapping // Fetch all the users—yes, all of them!
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping // Creating users, because we need more friends!
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        System.out.println("🔹 Received request: " + userRequest);

        Optional<User> savedUser = userService.createUser(userRequest);

        if (savedUser.isPresent()) {
            System.out.println("✅ User saved successfully: " + savedUser.get());
            return ResponseEntity.ok(savedUser.get());
        } else {
            System.out.println("❌ User could not be saved!");
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/department/{departmentId}") // Find all users in a department—teamwork makes the dream work!
    public ResponseEntity<List<User>> getUsersByDepartment(@PathVariable Long departmentId) {
        List<User> users = userService.getUsersByDepartment(departmentId);
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/by-email") // Because emails are basically digital addresses
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}") // Fetching users by their unique ID—like social security, but for our app
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-role") // Users have roles—just like in an RPG!
    public ResponseEntity<List<User>> getUsersByRole(@RequestParam String role) {
        try {
            Role userRole = Role.valueOf(role.toUpperCase());
            List<User> users = userService.getUsersByRole(userRole);
            return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Collections.emptyList()); // Invalid role? No soup for you!
        }
    }

    @GetMapping("/by-name") // Find users by their name—hopefully, no duplicates!
    public ResponseEntity<List<User>> getUsersByName(@RequestParam String name) {
        List<User> users = userService.getUsersByName(name);
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}") // Bye-bye user, it was nice knowing you!
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Poof! User is gone.
        } else {
            return ResponseEntity.notFound().build(); // Can’t delete what doesn’t exist!
        }
    }
}
