package com.example.DrivingHistory.service;

import com.example.DrivingHistory.entity.DrivingHistory;
import com.example.DrivingHistory.entity.UserDetails;
import com.example.DrivingHistory.entity.UserHistoryResponse;
import com.example.DrivingHistory.repository.DrivingHistoryRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@Slf4j

public class DrivingserviceImpli implements DrivingService {

    @Autowired
    private DrivingHistoryRepo drivingRepo;

    @Autowired
    private RestTemplate restTemplate;

    // Save Driving History with auto-calculated insurance price
    public DrivingHistory saveHistory(DrivingHistory history) {
        double price = 0;

        if (history.getAge() <= 25) {
            price = 5000;
        } else if (history.getAge() > 25 && history.getAge() <= 35 ) {
            price = 4000;
        } else {
            price = 6000; // e.g., for age > 60
        }

        history.setInsurancePrice(price);
        log.info("Assigned insurance price: {} for user {}", price, history.getUserId());

        return drivingRepo.save(history);
    }

    @Override
    public DrivingHistory getHistoryById(String id) {
        return drivingRepo.findById(id).orElse(null);
    }

    @Override
    public List<DrivingHistory> getHistoryByUser(Long id) {
        return drivingRepo.findByUserId(id);
    }

    @Override
    public List<DrivingHistory> getAll() {
        return drivingRepo.findAll();
    }

    @Override
    public void deleteHistory(String id) {
        drivingRepo.deleteById(id);
    }


    @Override
    public UserHistoryResponse getByLicenseNo(String licenseNo) {
        // Fetch driving history list from Mongo by licenseNo
        List<DrivingHistory> drivingHistoryList = drivingRepo.findByLicenseNo(licenseNo);

        // Fetch user details from User Service
        String url = "http://localhost:8081/api/users/licenseNo/" + licenseNo;
        UserDetails userDto = restTemplate.getForObject(url, UserDetails.class);

        // Return combined response DTO
        return new UserHistoryResponse(userDto, drivingHistoryList);
    }

}