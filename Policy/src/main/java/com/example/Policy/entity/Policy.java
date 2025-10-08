package com.example.Policy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "policies")
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    private Long userId;  // Reference to UserService userId
    private String policyNo;
    private String licenseNo;      // Used for driving history
    private double premium_amount;
    private String type;           // e.g. Car, Bike / Health
    private LocalDate startDate;
    private LocalDate endDate;
    private Long policyId;    // Dummy policy Id store

    @Transient
    private int age ;
}
