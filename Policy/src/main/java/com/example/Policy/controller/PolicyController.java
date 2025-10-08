package com.example.Policy.controller;

import com.example.Policy.entity.Policy;
import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import com.example.Policy.service.DummyPolicies;
import com.example.Policy.service.PolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {
    @Autowired
    private PolicyService policyService;
    @Autowired
    private DummyPolicies dummyPolicies;

    @PostMapping("/save")
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.savePolicy(policy);
    }

    @GetMapping("/{id}")
    public Policy getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    // Get policy by license number
    @GetMapping("/license/{licenseNo}")
    public List<Policy> getPoliciesByLicense(@PathVariable String licenseNo) {
        return policyService.getPoliciesByLicense(licenseNo);
    }

    @GetMapping("/all")
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    //  Get all policies by userId
    @GetMapping("/user/{userId}")
    public List<Policy> getPoliciesByUser(@PathVariable Long userId) {
        return policyService.getPoliciesByUser(userId);
    }


    @GetMapping("/details/policyId/{policyId}")
    public DummyPolicy policyDetails(@PathVariable Long policyId){
        return  policyService.policyDetails( policyId);
    }

}