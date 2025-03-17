package com.project.Company.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // This guy is the boss, handling web requests like a pro
public class PageController {

    @GetMapping("/") // When users visit the root URL, we send them home (literally)
    public String homePage() {
        return "index"; // Serves the main page—welcome home!
    }

    @GetMapping("/add-employee") // Because hiring through a URL is the future!
    public String addEmployeePage() {
        return "add-employee"; // Serves the add-employee page—HR would be proud
    }

    @GetMapping("/delete-user") // A page dedicated to saying goodbye... 💔
    public String showDeleteUserPage() {
        return "delete-user"; // Serves the delete-user page—press F to pay respects
    }
}