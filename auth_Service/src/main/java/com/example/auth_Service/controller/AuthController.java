package com.example.auth_Service.controller;

import com.example.auth_Service.dto.AuthResponse;
import com.example.auth_Service.dto.LoginRequest;
import com.example.auth_Service.dto.SignupRequest;
import com.example.auth_Service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {

    //http://localhost:8086/auth/login

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        return ResponseEntity.ok(
                Map.of("message", authService.signup(request))
        );
    }

    // LOGIN (CLEAN)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}