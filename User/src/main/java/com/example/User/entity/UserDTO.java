package com.example.User.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userid;

    private String fullName;
    private String email;
    private String phone;
    private String licenseNo; // used to check driving history
    private int age;


}
