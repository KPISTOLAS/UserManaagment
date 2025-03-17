package com.project.Company.repository;


import com.project.Company.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findById(Long id); // Get a department by its ID—because sometimes you need to know who’s in charge!
}
