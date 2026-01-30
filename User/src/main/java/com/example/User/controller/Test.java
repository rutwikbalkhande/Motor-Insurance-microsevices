package com.example.User.controller;

import com.example.User.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {


    @Autowired
     testService testser;

    @RestController
    public class TestController {

        @GetMapping("/user/test")
        public String test(
                @RequestHeader("X-USER-NAME") String username,
                @RequestHeader("X-USER-ROLE") String role) {

            return "Gateway OK | user=" + username + " | role=" + role;
        }
    }

    @GetMapping("/all")
    public List<?> getAllUser() {
        System.out.println("this is testing og jenkkins");
        log.warn("controller class " );
        return testser.getAllUser();
    }
}
