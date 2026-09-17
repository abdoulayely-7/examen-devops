package com.example.demo.application.mapper;

import com.example.demo.domain.model.User;
import com.example.demo.presentation.dto.request.CreateUserRequest;
import com.example.demo.presentation.dto.request.UpdateUserRequest;
import com.example.demo.presentation.dto.response.UserResponse;

public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(CreateUserRequest request) {

        return User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .build();
    }

    public static void updateEntityFromDto(UpdateUserRequest request, User user) {
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
    }

    public static UserResponse toResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail()
        );
    }
}
