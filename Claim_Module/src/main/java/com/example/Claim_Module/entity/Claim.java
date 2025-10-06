package com.example.Claim_Module.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.util.List;


@Table(name = "claims")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document(collection = "claimsDB")
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    private String claimNumber;
    private Long userId;
    private String policyNo;
    private String licenseNo;
    private double claimAmount;
    private String reason;
    private LocalDate incidentDate;
    private String status;

    public Claim(Claim claim, PolicyDto policyDto) {
    }
}