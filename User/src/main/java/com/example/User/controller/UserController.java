package com.example.User.controller;

import com.example.User.entity.PolicyDTO;
import com.example.User.entity.User;
import com.example.User.entity.UserDTO;
import com.example.User.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {


    private final UserService userSer;

    //  Save user // USER + ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/save")
    public User createUser(@RequestBody User user) {
        return userSer.createUser(user);
    }


    //  Get all users
    @PreAuthorize("hasRole('ADMIN')")     //  ADMIN only
    @GetMapping("/all")

    public List<User> getAllUser() {
        return userSer.getAllUser();
    }


    //  Get user by ID
    @GetMapping("/{userid}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public User getbyId(@PathVariable Long userid) {
        return userSer.getById(userid);
    }

    @GetMapping("/licenseNo/{license}")
    public User findBylicense(@PathVariable String license) {
        return userSer.findByLincense(license);
    }


    //  Delete user
    // ADMIN only
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userid}")
    public String delete(@PathVariable Long userid) {
        userSer.delete(userid);
        return "User deleted successfully";
    }

    //  Get user + policies (Privious purchase data)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/policy/{userId}")
    @CircuitBreaker(name= "userPolicyBreaker" , fallbackMethod = "userPolicyFallback")
    public User getPoliciesByUserId(@PathVariable Long userId) {
        return userSer.getPoliciesByUserId(userId);
    }

    //creating Fallback method for circuitbreaker
    public User userPolicyFallback(Long userId , Exception ex){

        log.warn("User Fallback is executed because Policy service is Down: {}", ex.getMessage());

        return User.builder()
                .userid(userId)
                .fullName("Dummy Name")
                .email("dummy@gmail.com")
                .age(40)
                .licenseNo("Dummy Data because Policy Service is Down")
                .build();
    }



    //policies for user  licenseNo & age use. generate price for user
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/policyLicenseNo/{licenseNo}")
    public List<PolicyDTO> getUserWithDummyPolicies(@PathVariable String licenseNo){
        log.info("call dummy policy : "+ licenseNo);

        return userSer.getUserWithDummyPolicies(licenseNo);

    }

    // Pageable List of all users
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/pageable")
    public Page<User> pageableList(@RequestParam("defaultValue=0") int page,
                                   @RequestParam("defaultValue=10") int size){

        return userSer. pageableList(page, size);
    }

    @PatchMapping("update/{userId}")
    public  ResponseEntity<User> partialUpdateUser(@PathVariable Long userId, @RequestBody UserDTO userDto){

        User updatedUser = userSer.partialUpdate(userId, userDto);

        return ResponseEntity.ok(updatedUser);
    }

}
