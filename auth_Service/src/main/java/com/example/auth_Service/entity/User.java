package com.example.auth_Service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "userAuth")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role; // ROLE_USER / ROLE_ADMIN

    // getters & setters
}