package com.project.Company.repository;

import com.project.Company.model.Role;
import com.project.Company.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); // Find a user by email—because emails are basically digital IDs

    List<User> findByDepartmentId(Long departmentId); // Get all users from a specific department (team spirit!)

    // New methods

    Optional<User> findById(Long id); // Already inherited from JpaRepository, but explicitly added for clarity

    List<User> findByRole(Role role); // Find users based on their role—Admin, User, or maybe Jedi?

    List<User> findByName(String name); // Because sometimes you just want to look up "John" and hope for the best
}
