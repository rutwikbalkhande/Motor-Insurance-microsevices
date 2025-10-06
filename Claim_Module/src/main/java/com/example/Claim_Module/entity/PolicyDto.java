package com.example.Claim_Module.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDto {
    private Long id;
    private Long userId;
    private String policyNo;
    private String licenseNo;
    private Double premium_amount;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private int age;
}