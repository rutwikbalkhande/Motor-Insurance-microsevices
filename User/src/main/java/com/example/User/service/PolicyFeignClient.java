package com.example.User.service;

import com.example.User.entity.PolicyDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;


@FeignClient(name="POLICY-SERVICE")
public interface PolicyFeignClient {

    @GetMapping("/policies/user/{userId}")
    List<PolicyDTO> getPoliciesByUser(@PathVariable Long userId);

}
