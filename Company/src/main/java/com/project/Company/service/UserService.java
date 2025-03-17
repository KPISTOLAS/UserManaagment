package com.project.Company.service;


import com.project.Company.model.Role;
import com.project.Company.model.User;
import com.project.Company.repository.DepartmentRepository;
import com.project.Company.repository.UserRepository;
import com.project.Company.request.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // This service is responsible for user-related operations (like creating, fetching, and deleting users)
@RequiredArgsConstructor // We’re using Lombok to generate a constructor that initializes final fields
@Transactional // This ensures all operations inside are managed by a transaction (rollback-friendly)
public class UserService {

    private final UserRepository userRepository; // Repository for managing users in the database
    private final DepartmentRepository departmentRepository; // Repository to fetch departments (users can belong to them)
    private final PasswordEncoder passwordEncoder; // For securely encoding passwords (no plaintext storage!)

    // Fetch all users in the system (your entire user base!)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by email (because emails are unique identifiers!)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get a user by their unique ID (everyone’s gotta have an ID)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get users based on their role (admin, manager, or regular user)
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // Get users by their name (searching for that one person, like "John Doe")
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // Get users by their department ID (because a department should know all its members)
    public List<User> getUsersByDepartment(Long departmentId) {
        return userRepository.findByDepartmentId(departmentId);
    }

    /**
     * Saves a new user with an encoded password and optional department association.
     *
     * @param request UserRequest DTO containing user details.
     * @return Optional<User> - Saved user if successful.
     */
    public Optional<User> createUser(UserRequest request) {
        System.out.println("🔹 Received user request: " + request);

        // Check for missing fields
        if (request.getEmail() == null || request.getPassword() == null || request.getName() == null) {
            System.out.println("❌ Missing required fields");
            return Optional.empty();
        }

        // Ensure no duplicate email addresses
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("❌ Email already exists: " + request.getEmail());
            return Optional.empty();
        }

        // Create the new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password!

        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase())); // Set the user's role (from the incoming string)
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid role: " + request.getRole());
            return Optional.empty();
        }

        // If there's a department ID, try to assign the user to that department
        if (request.getDepartmentId() != null) {
            departmentRepository.findById(request.getDepartmentId()).ifPresentOrElse(
                    user::setDepartment, // If department exists, assign it
                    () -> System.out.println("⚠️ Department not found: " + request.getDepartmentId()) // Log if department not found
            );
        }

        // Save the user and return the result
        User savedUser = userRepository.save(user);
        System.out.println("✅ User saved: " + savedUser);
        return Optional.of(savedUser);
    }

    // Delete a user by their ID
    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // User deleted successfully
        }
        return false; // User not found, deletion failed
    }
}
