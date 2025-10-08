package com.icwd.gateway.ApiGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Welcome")
public class welcome {

    @GetMapping
    public String SayWelcome(){
        return "Welocme to the Api Gateway...";
    }
}
