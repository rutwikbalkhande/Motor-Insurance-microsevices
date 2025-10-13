package com.example.User.service;

import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.exceptionHandler.PolicynotFoundException;
import com.example.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@FeignClient(name="POLICY-SERVICE")
public interface PolicyFeignClient {

    @GetMapping("/policies/user/{userId}")
    List<PolicyDTO> getPoliciesByUser(@PathVariable Long userId);

}
