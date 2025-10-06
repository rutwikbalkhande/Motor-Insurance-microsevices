package com.example.Claim_Module.controller;

import com.example.Claim_Module.entity.Claim;
import com.example.Claim_Module.entity.ClaimResponse;
import com.example.Claim_Module.entity.PolicyDto;
import com.example.Claim_Module.service.ClaimService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
@Slf4j
public class ClaimController {

    @Autowired
    private  ClaimService claimService;

    // Create / Save claim
    @PostMapping("/save")
    public Claim saveClaim(@RequestBody Claim claim) {
        log.info("Saving new claim for licenseNo: {}", claim.getLicenseNo());
        return claimService.saveClaim(claim);
    }

    // 🟢 Get all claims
    @GetMapping("/all")
    public List<Claim> getAllClaims() {
        log.info("Fetching all claims");
        return claimService.getAllClaims();
    }

    // 🟢 Get claim by claimId
    @GetMapping("/{claimId}")
    public Claim getClaimById(@PathVariable Long claimId) {
        log.info("Fetching claim by ID: {}", claimId);
        return claimService.getClaimById(claimId);
    }

    @GetMapping("/license/{licenseNo}")
    public List<Claim> getClaimsByLicense(@PathVariable String licenseNo) {
        log.info("Fetching all claims for license number: {}", licenseNo);
        return claimService.getClaimByLicense(licenseNo);
    }

    // 🟢 Update claim (status or amount)
    @PutMapping("/update/{claimId}")
    public Claim updateClaim(@PathVariable Long claimId,
                             @RequestParam(required = false) String status,
                             @RequestParam(required = false) Double claimAmount) {
        log.info("Updating claim ID: {} with status: {} and amount: {}", claimId, status, claimAmount);
        return claimService.updateClaim(claimId, status, claimAmount);
    }

   /* //  Get claim with policy details
    @GetMapping("/details/{claimId}")
    public Claim getClaimWithPolicy(@PathVariable String claimId) {
        log.info("Fetching claim with policy details for claim ID: {}", claimId);
        return claimService.getClaim(claimId);
    }

    */

}