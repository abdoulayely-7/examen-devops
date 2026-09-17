package com.example.demo.presentation.controller;

import com.example.demo.application.service.UserService;
import com.example.demo.common.response.ApiResponse;
import com.example.demo.presentation.dto.request.CreateUserRequest;
import com.example.demo.presentation.dto.request.UpdateUserRequest;
import com.example.demo.presentation.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
        // test

        private final UserService userService;

        @PostMapping
        public ResponseEntity<ApiResponse<UserResponse>> createUser(
                        @Valid @RequestBody CreateUserRequest request) {

                UserResponse response = userService.createUser(request);

                ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                                .success(true)
                                .message("User created successfully")
                                .data(response)
                                .build();

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(apiResponse);
        }

        @GetMapping
        public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
                List<UserResponse> users = userService.getAllUsers();

                ApiResponse<List<UserResponse>> apiResponse = ApiResponse.<List<UserResponse>>builder()
                                .success(true)
                                .message("Users retrieved successfully")
                                .data(users)
                                .build();

                return ResponseEntity.ok(apiResponse);
        }

        @PutMapping("/{id}")
        public ResponseEntity<ApiResponse<UserResponse>> updateUser(
                        @PathVariable Long id,
                        @Valid @RequestBody UpdateUserRequest request) {
                UserResponse response = userService.updateUser(id, request);

                ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                                .success(true)
                                .message("User updated successfully")
                                .data(response)
                                .build();

                return ResponseEntity.ok(apiResponse);
        }
}
