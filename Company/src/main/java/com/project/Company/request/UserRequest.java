package com.project.Company.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String name; // The user's name—because every user should have an identity!

    private String email; // Email for user identification—no email, no entry!

    private String password; // Password for security—keep it secret, keep it safe!

    private String role; // Role of the user—Admin, User, or maybe something cool like "Superuser"?

    private Long departmentId; // Which department does this user belong to? We need to know where they work!
}
