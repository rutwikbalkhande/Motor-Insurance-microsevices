package com.example.DrivingHistory.repository;

import com.example.DrivingHistory.entity.DrivingHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrivingHistoryRepo extends MongoRepository<DrivingHistory, String >
{
    // fetch by userId
    List<DrivingHistory> findByUserId(Long userId);

    // fetch by licenseNo
    List<DrivingHistory> findByLicenseNo(String licenseNo);
}

