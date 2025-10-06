package com.example.Policy.service;

import com.example.Policy.entity.Policy;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DummyPolicies {

    // Generate dummy policies based on user age
    public List<Policy> generateDummyPolicies(String licenseNo, int age) {
        double premium;
        if (age < 25) {
            premium = 5000;
        } else if (age < 40) {
            premium = 3000;
        } else {
            premium = 2000;
        }

        List<Policy> policies = new ArrayList<>();

        Policy carPolicy = new Policy();

        carPolicy.setUserId(System.currentTimeMillis());  //random userId generate
        carPolicy.setPolicyNo("CAR-"   + "-001");
        carPolicy.setLicenseNo("LIC-" + licenseNo);
        carPolicy.setAge(age);
        carPolicy.setPremium_amount(premium);
        carPolicy.setType("Car");
        carPolicy.setStartDate(LocalDate.now());
        carPolicy.setEndDate(LocalDate.now().plusYears(1));
        policies.add(carPolicy);

        Policy bikePolicy = new Policy();

        bikePolicy.setUserId(System.currentTimeMillis());
        bikePolicy.setPolicyNo("BIKE-"+ "-002");
        bikePolicy.setLicenseNo("LIC-" + licenseNo);
        bikePolicy.setAge(age);
        bikePolicy.setPremium_amount(premium * 0.8);
        bikePolicy.setType("Bike");
        bikePolicy.setStartDate(LocalDate.now());
        bikePolicy.setEndDate(LocalDate.now().plusYears(1));
        policies.add(bikePolicy);

        return policies;
    }
}