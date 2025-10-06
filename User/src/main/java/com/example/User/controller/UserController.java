package com.example.User.controller;

import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.service.PolicyDataClient;
import com.example.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userSer;
    private PolicyDataClient policyDataService;

    //  Save user
    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        return userSer.createUser(user);
    }

    //  Get all users
    @GetMapping("/all")
    public List<User> getAllUser() {
        return userSer.getAllUser();
    }

    //  Get user by ID
    @GetMapping("/{userid}")
    public User getbyId(@PathVariable Long userid) {
        return userSer.getById(userid);
    }

    @GetMapping("licenseNo/{license}")
    public User findBylicense(@PathVariable String license) {
        return userSer.findByLincense(license);
    }


    //  Delete user
    @DeleteMapping("/{userid}")
    public String delete(@PathVariable Long userid) {
        userSer.delete(userid);
        return "User deleted successfully";
    }

    //  Get user + policies (Privious purchase data)
    @GetMapping("/policy/{userId}")
    public User getPoliciesByUserId(@PathVariable Long userId) {
        return userSer.getPoliciesByUserId(userId);
    }

    //dummy policies licenseNo & age generate prise for customer
    @GetMapping("/newPolicies/{licenseNo}/{age}")
    public List<PolicyDTO> getUserWithDummyPolicies(@PathVariable String licenseNo, @PathVariable int age){
        return userSer.getUserWithDummyPolicies(licenseNo, age);
    }
}
