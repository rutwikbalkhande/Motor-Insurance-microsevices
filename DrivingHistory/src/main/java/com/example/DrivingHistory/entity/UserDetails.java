package com.example.DrivingHistory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private String fullName;
    private String email;
    private String phone;
    private String licenseNo; // used to check driving history
    private int age;



}
