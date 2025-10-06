package com.example.User.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {

    private Long id;  // Primary key

    private Long userId;  // Reference to UserService userId
    private String policyNo;
    private String licenseNo;      // Used for driving history
    private double premium_amount;
    private String type;           // e.g. Car, Bike
    private LocalDate startDate;
    private LocalDate endDate;

}
