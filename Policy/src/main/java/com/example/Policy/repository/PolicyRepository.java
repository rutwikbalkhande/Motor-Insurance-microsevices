package com.example.Policy.repository;

import com.example.Policy.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>
{
    List<Policy> findByUserId(Long userId);

    List<Policy> findByLicenseNo(String licenseNo);

}
