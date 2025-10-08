package com.example.DrivingHistory.service;

import com.example.DrivingHistory.entity.DrivingHistory;
import com.example.DrivingHistory.entity.UserHistoryDTO;

import java.util.List;

public interface DrivingService {

    DrivingHistory saveHistory(DrivingHistory history);
    DrivingHistory getHistoryById(String id);
    List<DrivingHistory> getHistoryByUser(Long userId);
    List<DrivingHistory> getAll();
    void deleteHistory(String id);

    UserHistoryDTO getByLicenseNo(String licenseNo);

    DrivingHistory searchByLicense(String licenseNo);
}
