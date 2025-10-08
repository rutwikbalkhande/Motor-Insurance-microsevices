package com.example.Policy.controller;

import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import com.example.Policy.service.DummyPolicies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/policies/dummy")
public class DummyController {

    @Autowired
    private DummyPolicies dummyPolicies;

    @PostMapping("/save")
    public DummyPolicy createPolicy(@RequestBody  DummyPolicy policy){
        return dummyPolicies.createPolicy(policy);
    }

    @GetMapping("/all")
    public List<DummyPolicy> getAllPolicies(){

        return dummyPolicies.getAllPolicies();
    }



    // Dummy endpoint
    @GetMapping("/generate/{userId}/{licenseNo}")
    public List<DummyPolicy> generatePolicies(@PathVariable Long userId ,@PathVariable String licenseNo ) {
        log.info("controller generatePolicies create policy for user: "+ userId +  licenseNo);

        return dummyPolicies.generateAndSavePolicies(userId, licenseNo);
    }

}
