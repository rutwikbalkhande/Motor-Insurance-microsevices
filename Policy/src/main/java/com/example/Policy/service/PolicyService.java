package com.example.Policy.service;


import com.example.Policy.entity.Policy;
import com.example.Policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PolicyService {

    Policy savePolicy(Policy policy);

    List<Policy> getAllPolicies();

    Policy getPolicyById(Long id);

    List<Policy> getPoliciesByUser(Long id);

    List<Policy> getPoliciesByLicense(String licenseNo);
}
