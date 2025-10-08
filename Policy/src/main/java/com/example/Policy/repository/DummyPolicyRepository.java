package com.example.Policy.repository;

import com.example.Policy.entity.dummyPolicy.DummyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DummyPolicyRepository extends JpaRepository<DummyPolicy, Long> {

    List<DummyPolicy> findByUserId(Long userId);

}
