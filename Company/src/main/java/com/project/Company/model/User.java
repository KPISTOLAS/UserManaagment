package com.project.Company.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // The table where all our awesome users live
@Getter
@Setter
@NoArgsConstructor // No-arg constructor for JPA and other magic frameworks
@AllArgsConstructor // All-arg constructor because convenience matters
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key—let the database handle it!
    private Long id;

    @Column(nullable = false, unique = true) // Every user needs an email, and no two users can share one!
    private String email;

    @Column(nullable = false) // No password? No access! (But don’t store raw passwords—hash them!)
    private String password;

    @Enumerated(EnumType.STRING) // Store role as a string (easier to read in the database)
    @Column(nullable = false) // Every user has a role—like Admin, User, or Superhero
    private Role role;

    @ManyToOne // A user belongs to a department, but a department has many users
    @JoinColumn(name = "department_id") // Foreign key linking users to departments
    private Department department;

    @Column(nullable = false) // Because every user deserves a name!
    private String name;
}