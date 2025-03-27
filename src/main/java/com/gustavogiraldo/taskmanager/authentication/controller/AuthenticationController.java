package com.gustavogiraldo.taskmanager.authentication.controller;

import com.gustavogiraldo.taskmanager.authentication.dto.AuthRequest;
import com.gustavogiraldo.taskmanager.authentication.dto.AuthResponse;
import com.gustavogiraldo.taskmanager.authentication.dto.RegisterRequest;
import com.gustavogiraldo.taskmanager.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
}