package com.example.DrivingHistory.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("DrivingHistory")   // Mongo DB using
//@Entity
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
