package com.example.User.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder            //Builder is a Lombok annotation that lets you build objects easily
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    private String fullName;
    private String email;
    private String phone;
    private String licenseNo; // used to check driving history
    private int age;

    @Transient
    private List<PolicyDTO> policies;

}
