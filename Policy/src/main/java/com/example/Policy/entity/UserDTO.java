package com.example.Policy.entity;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data// UserDTO use for kafka consumer
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userid;

    private String fullName;
    private String email;
    private String phone;
    private String licenseNo; // used to check driving history
    private int age;

    private List<?> policies;

}
