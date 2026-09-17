package com.example.demo.presentation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(
    @Size(min = 2, max = 100, message = "Username must be between 2 and 100 characters")
    String username,
    
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    String password,
    
    @Email(message = "Invalid email format")
    String email
) {
}