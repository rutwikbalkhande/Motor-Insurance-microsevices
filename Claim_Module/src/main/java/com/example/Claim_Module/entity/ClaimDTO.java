package com.example.Claim_Module.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClaimDTO {

    private Long claimId;

    private String claimNumber;
    private Long userId;
    private String policyNo;
    private String licenseNo;
    private double claimAmount;
    private String reason;
    private LocalDate incidentDate;
    private String status;



    //  Convert DTO → Entity
    public Claim toEntity() {
        Claim claim = new Claim();
        claim.setClaimId(this.claimId);
        claim.setClaimNumber(this.claimNumber);
        claim.setUserId(this.userId);
        claim.setPolicyNo(this.policyNo);
        claim.setLicenseNo(this.licenseNo);
        claim.setClaimAmount(this.claimAmount);
        claim.setReason(this.reason);
        claim.setIncidentDate(this.incidentDate);
        claim.setStatus(this.status);
        return claim;
    }

    //  Convert Entity → DTO
    public static ClaimDTO fromEntity(Claim entity) {
        ClaimDTO dto = new ClaimDTO();
        dto.setClaimId(entity.getClaimId());
        dto.setClaimNumber(entity.getClaimNumber());
        dto.setUserId(entity.getUserId());
        dto.setPolicyNo(entity.getPolicyNo());
        dto.setLicenseNo(entity.getLicenseNo());
        dto.setClaimAmount(entity.getClaimAmount());
        dto.setReason(entity.getReason());
        dto.setIncidentDate(entity.getIncidentDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
