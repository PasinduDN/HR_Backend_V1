package com.example.hrmanagement.dto;

import com.example.hrmanagement.entity.UserRole;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    // Optional: default to HR if not provided
    private UserRole role;
}

