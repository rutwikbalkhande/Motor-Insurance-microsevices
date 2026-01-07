package com.example.User.controller;

import com.example.User.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class testService {

    @Autowired
    @Qualifier("restTemplate")
    public RestTemplate restTemplate;

    public List<Object> getAllUser() {


        String url = "https://jsonplaceholder.typicode.com/users";

        log.warn("Calling API: {}", url);

        try {
            User[] userArray = restTemplate.getForObject(url, User[].class);

            log.warn("Users received = {}", userArray.length);

            return Collections.singletonList((ArrayList<User>) Arrays.asList(userArray));

        } catch (Exception e) {
            log.error("API Error: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}