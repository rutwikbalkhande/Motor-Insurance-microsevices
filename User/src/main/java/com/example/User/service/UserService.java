package com.example.User.service;

import com.example.User.annotation.AuditAction;
import com.example.User.annotation.LogExecutionTime;
import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import com.example.User.exceptionHandler.InvalidInputException;
import com.example.User.exceptionHandler.PolicynotFoundException;
import com.example.User.exceptionHandler.UserNotFoundException;
import com.example.User.mapper.UserMapper;
import com.example.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PolicyFeignClient policyFeignClient;
    private final UserMapper mapper;
    @Autowired
    public RestTemplate restTemplate;

    //save user
    @Cacheable(value = "User" , key= "#id" )
    @AuditAction(action= "create")
    public User createUser(User user) {
        return userRepo.save(user);
    }

    //All user
    @LogExecutionTime
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    //user by ID
    @LogExecutionTime
    public User getById(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId) );
        return  user;

    }
    @LogExecutionTime
    public User findByLincense(String license)
    {
        return userRepo.findByLicenseNo(license);
    }

    //delete
    @AuditAction(action= "delete")
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }

    // Pageable
    @LogExecutionTime
    public Page<User> pageableList(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return userRepo.findAll(pageable);
    }

    @LogExecutionTime
    public User getPoliciesByUserId(Long userId)
    {
        //get user from Mysql
        User user=  userRepo.findById(userId).orElseThrow(
                ()-> new RuntimeException("user not Found ! with ID:"+ userId));

/*
        // fetching Policies using RestTemplate user id from Policy Module Database
        String url= "http://POLICY-SERVICE/policies/user/" + userId;      // String url= "http://localhost:8083/policies/user/" + userId;


        ArrayList<PolicyDTO> policies = restTemplate.getForObject(url, ArrayList.class);
*/

        //call using feign Client
       List<PolicyDTO> policies= policyFeignClient.getPoliciesByUser(userId);

        if(policies== null || policies.isEmpty())
        {
            throw new PolicynotFoundException("user does not Buy any Policies");
        }
        user.setPolicies(policies);

        return user;
    }



    // fetching Dummy policies for user to check available policies & price based on licenceNo and age.
    @LogExecutionTime
    public List<PolicyDTO> getUserWithDummyPolicies(String licenseNo ) {
      //  User user = userRepo.findByLicenseNo(licenseNo);

        String url= "http://POLICY-SERVICE/policies/dummy/generate/"+ licenseNo ;     // call Policy service - DummyController

        return  restTemplate.getForObject(url,ArrayList.class);             // use RestTemplet to call other mocroservice

    }

    public User partialUpdate(Long userId, UserDTO userDto){

      User user =  userRepo.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));

        // ✅ MapStruct update
        mapper.updateUserFromDto(userDto, user);

        return userRepo.save(user);
    }
}