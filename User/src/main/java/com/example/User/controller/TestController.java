package com.example.User.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    // USER + ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/profile")
    public String myProfile(
            @RequestHeader("X-USER-NAME") String username , @RequestHeader("X-USER-ROLE") String role) {

        return "Profile of user: " + username + " , role: " + role;
    }

    // ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public String allUsers() {
        return "All users data (ADMIN only)";
    }
}
