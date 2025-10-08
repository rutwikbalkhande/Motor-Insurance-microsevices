package com.example.Policy.service;

import com.example.Policy.entity.dummyPolicy.DrivingHistoryDto;;
import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import com.example.Policy.repository.DummyPolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DummyPolicies {

    @Autowired
    private DummyPolicyRepository dummyPolicyRepo;

    @Autowired
    public RestTemplate restTemplate;

    public DummyPolicy createPolicy(DummyPolicy policy){

        return dummyPolicyRepo.save(policy);
    }

    public List<DummyPolicy> getAllPolicies() {
        return dummyPolicyRepo.findAll();
    }

    public List<DummyPolicy> PoliciesForUser() {
        return dummyPolicyRepo.findAll();
    }


    public List<DummyPolicy> generateAndSavePolicies(Long userId , String licenseNo ) {
        String url = "http://DRIVINGHISTORY-SERVICE/driving-history/search/" + licenseNo;

        DrivingHistoryDto response = restTemplate.getForObject(url, DrivingHistoryDto.class);
        log.info("Policy = driving history data =" + response);

        if (response == null) {
            throw new RuntimeException("No driving history found for license: " + licenseNo);
        }
            int age = response.getAge();

            double basePremium;
            if (age <= 25) {
                basePremium = 5000;
            } else if (age <= 40) {
                basePremium = 3000;
            } else {
                basePremium = 7000;
            }

            List<DummyPolicy> policies = new ArrayList<>();

            // Car Policy
            DummyPolicy carPolicy = new DummyPolicy();
            carPolicy.setPolicyId(userId);
            carPolicy.setPolicyName("Car Insurance");
            carPolicy.setBasePremium(basePremium);
            carPolicy.setAdjustedPremium(basePremium * 1.2);
            carPolicy.setDescription("Comprehensive car insurance for license: " + licenseNo);
            carPolicy.setAge(response.getAge());
            policies.add(carPolicy);

            // Bike Policy
            DummyPolicy bikePolicy = new DummyPolicy();
            bikePolicy.setUserId(userId);
            bikePolicy.setPolicyName("Bike Insurance");
            bikePolicy.setBasePremium(basePremium * 0.8);
            bikePolicy.setAdjustedPremium(basePremium);
            bikePolicy.setDescription("Two-wheeler insurance for license: " + licenseNo);
            bikePolicy.setAge(age);
            policies.add(bikePolicy);

            // Health Policy
            DummyPolicy healthPolicy = new DummyPolicy();
            healthPolicy.setUserId(userId);
            healthPolicy.setPolicyName("Health Insurance");
            healthPolicy.setBasePremium(basePremium * 1.5);
            healthPolicy.setAdjustedPremium(basePremium * 1.3);
            healthPolicy.setDescription("Health coverage for person aged " + age);
            healthPolicy.setAge(age);
            policies.add(healthPolicy);


            // Save and return dummy policies
            return dummyPolicyRepo.saveAll(policies);
        }
    }
