package com.example.Claim_Module.repository;

import com.example.Claim_Module.entity.Claim;
import com.example.Claim_Module.entity.ClaimResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
 public interface ClaimMysqlRepository extends JpaRepository<Claim , Long> {
    List<Claim> findByUserId(Long userId);

    List<Claim> findByPolicyNo(String policyNo);

    List<Claim> findByLicenseNo(String licenseNo);
}
