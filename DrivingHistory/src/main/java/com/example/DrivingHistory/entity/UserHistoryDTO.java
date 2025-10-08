package com.example.DrivingHistory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserHistoryDTO {

    private UserDetails user;
    private List<DrivingHistory> drivingHistoryList;
    private String message;

    // Custom constructor for normal responses
    public UserHistoryDTO(UserDetails user, List<DrivingHistory> drivingHistoryList) {
        this.user = user;
        this.drivingHistoryList = drivingHistoryList;
    }
}
