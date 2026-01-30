package com.example.auth_Service.test.controller;

import com.example.auth_Service.test.entity.TestAuth;
import com.example.auth_Service.test.service.TestAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/testauth")
public class HomeController {

    //localhost:8086/testauth/user

    @Autowired
    private TestAuthService userservice;

    @GetMapping("/user")
    public List<TestAuth> getuser()
    {
        return userservice.getuser();
    }

    @GetMapping("/currentUser")
    public String cureentLogedIn(Principal principal)
    {
        return userservice.currentlogedin(principal);
    }
}
