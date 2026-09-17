package com.example.demo.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("service", "examen-devops-back");
        body.put("environment", "production");
        body.put("message", "Application backend Spring Boot est en ligne et operationnelle !");
        body.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(body);
    }
}
