package com.example.User.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDTO {
    private Long claimId;

    private Long userId;
    private String policyNo;
    private String licenseNo;
    private double claimAmount;
    private String status;
}
