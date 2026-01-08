package com.example.Policy.controller;

import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import com.example.Policy.service.DummyPolicies;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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



    // Dummy endpoint pass userId & license No to fetch data from DrivingHistory-Service
    @GetMapping("/generate/{userId}/{licenseNo}")
    @CircuitBreaker(name = "policyByHistory", fallbackMethod = "DrivingDetailsFallback")
    public List<DummyPolicy> generatePolicies(@PathVariable Long userId ,@PathVariable String licenseNo ) {
        log.info("controller generatePolicies create policy for user: "+ userId +  licenseNo);

        return dummyPolicies.generateAndSavePolicies(userId, licenseNo);
    }

    // FallBack method if policy servic down the this dummy data
    public List<DummyPolicy> DrivingDetailsFallback(Long userId, Exception ex) {
        log.warn("Fallback triggered for userId {} because Policy Service is DOWN: {}", userId, ex.getMessage());

        DummyPolicy fallbackPolicy = DummyPolicy.builder()
                .userId(userId)
                .policyName("Fallback Policy")
                .basePremium(0.0)
                .adjustedPremium(0.0)
                .description("Driving History Service is currently unavailable. Showing fallback policy data.")
                .age(0)
                .build();

        return List.of(fallbackPolicy);
    }

}
