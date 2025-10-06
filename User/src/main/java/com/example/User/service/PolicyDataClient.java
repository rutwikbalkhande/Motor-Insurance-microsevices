package com.example.User.service;

import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PolicyDataClient {
// PolicyDTO use for: user & Policies Data
/*
    @Autowired
    private  UserRepository userRepo;
    @Autowired
    private  PolicyDataClient policyDataClient;
    @Autowired
    public RestTemplate restTemplate;


    public User getPoliciesByUserId(Long userId)
    {
        //get user from Mysql
        User user=  userRepo.findById(userId).orElseThrow(
                ()-> new RuntimeException("user not Found ! with ID:"+ userId));

        // fetching Policies using user id from Policy Module Database
        String url= "http://localhost:8083/policies/user/" + userId;

        ArrayList<PolicyDTO> policies = restTemplate.getForObject(url, ArrayList.class);
        user.setPolicies(policies);

        return user;
    }


    // fetching Dummy policies for user to check available policies & price based on licenceNo and age.
    public User getUserWithDummyPolicies(String licenseNo, int age) {
        User user = userRepo.findByLicenseNo(licenseNo);
        String url= "http://localhost:8083/policies/dummy/"+ licenseNo + "/" + age ;


        ArrayList<PolicyDTO> dummyPolicies   = restTemplate.getForObject(url,ArrayList.class);
        user.setPolicies(dummyPolicies);
        return user;
    }
*/
}
