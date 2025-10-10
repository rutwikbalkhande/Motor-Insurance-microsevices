package com.example.Claim_Module.service;


import com.example.Claim_Module.ExceptionHandler.customException.ClaimNotFoundException;
import com.example.Claim_Module.entity.Claim;
import com.example.Claim_Module.entity.ClaimResponse;
import com.example.Claim_Module.entity.PolicyDto;
import com.example.Claim_Module.repository.ClaimMysqlRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.metamodel.internal.StandardEmbeddableInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class ClaimService {

    @Autowired
    private ClaimMysqlRepository repository;
    @Autowired
    private RestTemplate restTemplate;

 //   String  POLICY_URL= "http://localhost:8083/policies/license/";

    //  Save claim using only license number
    public Claim saveClaim(Claim claim) {
        try {
              // Generate claim number if not provided
            if (claim.getClaimNumber() == null || claim.getClaimNumber().isEmpty()) {
                claim.setClaimNumber("CLM-" + UUID.randomUUID().toString().substring(0, 8));
            }

            // Set default status
            if (claim.getStatus() == null || claim.getStatus().isEmpty()) {
                claim.setStatus("PENDING");
            }
            //  Call Policy Service using licenseNo
            String url = "http://localhost:8083/policies/license/" + claim.getLicenseNo();
            PolicyDto[] policyArray = restTemplate.getForObject(url, PolicyDto[].class);

            if (policyArray != null && policyArray.length > 0) {
              PolicyDto policy = policyArray[0]; // pick first policy

                // Auto-fill claim details
                claim.setUserId(policy.getUserId());
                claim.setPolicyNo(policy.getPolicyNo());
                //      claim.setClaimAmount(policy.getPremium_amount() * 10000);
            }
            else {
                System.out.println(" No policy found for licenseNo: " + claim.getLicenseNo());
            }

            // Save claim
            return repository.save(claim);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while saving claim: " + e.getMessage());
        }
    }


    //All claims
    public List<Claim> getAllClaims() {
        log.info("Fetching all claims from database");
        return repository.findAll();
    }


    //  Get claim by claimId
        public Claim getClaimById(Long claimId) {

             log.info("fetching claim by claim ID: " + claimId);
            return repository.findById(claimId)
                    .orElseThrow(() -> new ClaimNotFoundException("Claim not found with id: " + claimId));
        }

    // Get claims by license number (can return multiple)
    public List<Claim> getClaimByLicense(String licenseNo) {
        log.info("Fetching claims by license number: {}", licenseNo);
        List<Claim> claims = repository.findByLicenseNo(licenseNo);

        return claims;
    }

        //  Update claim (status, amount)
        public Claim updateClaim(Long claimId, String status, Double claimAmount) {

            log.info("Updating claim ID: {} with status: {} and amount: {}", claimId, status, claimAmount);
             Claim claim = getClaimById(claimId);

            if (status != null && !status.isEmpty()) claim.setStatus(status);
            if (claimAmount != null) claim.setClaimAmount(claimAmount);
            Claim updated=  repository.save(claim);

            log.info("data updated succesfully {}", updated);
            return updated;

        }

/*
        //  Optional: get Claim + Policy DTO
        public Claim getClaim(String claimId) {
            //  Fetch claim from DB
            Claim claim = getClaim(claimId);

            // Initialize PolicyDto as null
            PolicyDto policyDto = null;

            //  If claim has a policy number, call Policy Service via REST
            if (claim.getPolicyNo() != null) {
                String url = "http://localhost:8083/policies/license/" + claim.getPolicyNo();
                policyDto = restTemplate.getForObject(url, PolicyDto.class);
            }

            //  Combine both into a ClaimResponse
            return new Claim(claim, policyDto);
        }

*/
    }
