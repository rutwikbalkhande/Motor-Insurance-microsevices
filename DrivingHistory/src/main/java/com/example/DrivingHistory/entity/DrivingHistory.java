package com.example.DrivingHistory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("DrivingHistory")
public class DrivingHistory {

    @Id
    private String id;

    private Long userId;       // Link with User Service
    private String violations; // e.g., "Speeding, Drunk Driving"
    private String licenseStatus; // e.g., "Valid" or "Suspended"
    private String licenseNo;      // New field
    private int age;
    private double insurancePrice;   // Auto-calculated


}
