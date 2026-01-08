package com.example.Policy.entity.dummyPolicy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DummyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private Long userId;
    private String policyName;
    private double basePremium;
    private double adjustedPremium;
    private String description;
    private int age;
}