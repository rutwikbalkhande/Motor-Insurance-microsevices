package com.example.DrivingHistory.service;

import com.example.DrivingHistory.entity.DrivingHistory;
import com.example.DrivingHistory.entity.UserDetails;
import com.example.DrivingHistory.entity.UserHistoryDTO;
import com.example.DrivingHistory.exceptionHandler.DetailsNotFoundExe;
import com.example.DrivingHistory.repository.DrivingHistoryRepo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        return drivingRepo.findById(id).
                              orElseThrow(()->new DetailsNotFoundExe("user driving Record not Available " + id));
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
    public UserHistoryDTO getByLicenseNo(String licenseNo) {
        // Fetch driving history list from Mongo by licenseNo
        List<DrivingHistory> drivingHistoryList = drivingRepo.findByLicenseNo(licenseNo);


        // Fetch user details from user-Service
        String url = "http://USER-SERVICE/users/licenseNo/" + licenseNo;
        UserDetails userDto = restTemplate.getForObject(url, UserDetails.class);

        if(drivingHistoryList == null || drivingHistoryList.isEmpty()){
                 return new UserHistoryDTO(userDto, new ArrayList<>(),"No Driving violation Found !");
        }

        // Return combined response DTO
        return new UserHistoryDTO(userDto, drivingHistoryList);
    }

    @Override
    public DrivingHistory searchByLicense(String licenseNo) {
        return drivingRepo.findFirstByLicenseNo(licenseNo);
    }

}