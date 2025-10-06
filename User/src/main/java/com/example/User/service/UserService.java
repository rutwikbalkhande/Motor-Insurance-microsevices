package com.example.User.service;

import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    @Autowired
    public RestTemplate restTemplate;

    //save user
    public User createUser(User user) {
        return userRepo.save(user);
    }

    //All user
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    //user by ID
    public User getById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        return  user;

    }
    public User findByLincense(String license)
    {
        return userRepo.findByLicenseNo(license);
    }

    //delete
    public void delete(Long userId) {
        userRepo.deleteById(userId);
    }




    public User getPoliciesByUserId(Long userId)
    {
        //get user from Mysql
        User user=  userRepo.findById(userId).orElseThrow(
                ()-> new RuntimeException("user not Found ! with ID:"+ userId));

        // fetching Policies using user id from Policy Module Database
        String url= "http://localhost:8083/policies/user/" + userId;

        ArrayList<PolicyDTO> policies = restTemplate.getForObject(url, ArrayList.class);
        user.setPolicies(policies);

        return user;
    }


    // fetching Dummy policies for user to check available policies & price based on licenceNo and age.
    public List<PolicyDTO> getUserWithDummyPolicies(String licenseNo, int age) {
      //  User user = userRepo.findByLicenseNo(licenseNo);
        String url= "http://localhost:8083/policies/dummy/"+ licenseNo + "/" + age ;

        return  restTemplate.getForObject(url,ArrayList.class);


    }



}