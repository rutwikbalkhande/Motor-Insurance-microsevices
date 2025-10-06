package com.example.DrivingHistory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistoryResponse {

    private UserDetails user;
    private List<DrivingHistory> drivingHistoryList;
}
