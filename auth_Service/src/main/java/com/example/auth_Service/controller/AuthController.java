package com.example.auth_Service.controller;

import com.example.auth_Service.dto.LoginRequest;
import com.example.auth_Service.entity.SignupRequest;
import com.example.auth_Service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    //http://localhost:8086/auth/login

    @Autowired
    private AuthService authService;

    //SignUp create new username password  "unique"
    @PostMapping("/signup")
    public String signUp(@RequestBody SignupRequest request)
    {

        return authService.signup(request);
    }

    // login using username, password
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request)
    {
        System.out.println("LOGIN API HIT");
        return authService.login(request.getUsername(), request.getPassword());
    }

    // not working
    //check current user who logged in
    @GetMapping("/currentuser")
    public String cureentLogeIn(Principal principal)
    {
        return authService.currentlogein(principal);
    }
}

