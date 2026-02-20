package com.example.auth_Service.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;
    private String password;
    private String role;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

}
