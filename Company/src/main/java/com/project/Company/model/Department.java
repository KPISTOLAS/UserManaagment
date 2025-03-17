package com.project.Company.model;

import jakarta.persistence.*;

@Entity
@Table(name = "departments") // Because every organization needs departments!
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment because we love automation
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100) // Every department has a unique name (no duplicates allowed!)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)  // One manager can manage multiple departments, but we fetch lazily to save resources
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "fk_manager_id"))  // Enforcing a foreign key constraint—no orphaned departments!
    private User manager;

    // Getters and Setters—because encapsulation is key 🔑
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + '\'' + ", manager=" + (manager != null ? manager.getId() : "null") + '}';
    }
}
