package com.example.User.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserWithPolicyDTO {

    User user;
    List<PolicyDTO> policies;
}
