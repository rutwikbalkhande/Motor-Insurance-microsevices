package com.example.Policy.service;

import com.example.Policy.entity.Policy;
import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import com.example.Policy.repository.DummyPolicyRepository;
import com.example.Policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PolicyServiceImpli implements PolicyService {


    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private DummyPolicyRepository dummyRepo;

    public Policy savePolicy(Policy policy) {
        if (policy.getPolicyNo() == null || policy.getPolicyNo().isEmpty()) {
            policy.setPolicyNo(UUID.randomUUID().toString().substring(0, 7));
        }
        return policyRepository.save(policy);
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElse(null);
    }

    public List<Policy> getPoliciesByUser(Long userId) {
        return policyRepository.findByUserId(userId);
    }

    // Fetch policy by license number
    public List<Policy> getPoliciesByLicense(String licenseNo) {
        List<Policy> policies = policyRepository.findByLicenseNo(licenseNo);
        if (policies.isEmpty()) {
            throw new RuntimeException("No policy found for license number: " + licenseNo);
        }
        return policies;
    }

    @Override
    public DummyPolicy policyDetails(Long policyId) {
        return dummyRepo.findById(policyId).orElseThrow(() ->
                                       new RuntimeException("Policy not found with ID: " + policyId));
    }
}
