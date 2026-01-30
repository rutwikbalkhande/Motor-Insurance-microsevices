package com.example.auth_Service.service;

import com.example.auth_Service.entity.SignupRequest;
import com.example.auth_Service.entity.User;
import com.example.auth_Service.repository.UserRepository;
import com.example.auth_Service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //SignUp create new username password  "unique"
    public String signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User Already Exist !");
        } else {
            User user = new User();

            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            if(request.getRole().isBlank()){
                user.setRole("user");
            }
            else{
                user.setRole(request.getRole());
            }

            userRepository.save(user);
            return "User Register Successfully..";
        }
    }

// login using username, password
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid Username"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    // check who loged in
    public String currentlogein(Principal principal)
    {
        return principal.getName();
    }

}
