package com.example.demo.presentation.dto.response;

public record UserResponse(
    Long id,
    String username,
    String password,
    String email
) {
}
