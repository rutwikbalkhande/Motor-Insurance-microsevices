package com.example.Policy.entity.dummyPolicy;

import com.example.Policy.service.DummyPolicies;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class DrivingHistoryDto {

    private String id;

    private Long userId;       // Link with User Service
    private String violations; // e.g., "Speeding, Drunk Driving"
    private String licenseStatus; // e.g., "Valid" or "Suspended"
    private String licenseNo;      // New field
    private int age;
    private double insurancePrice;
}
