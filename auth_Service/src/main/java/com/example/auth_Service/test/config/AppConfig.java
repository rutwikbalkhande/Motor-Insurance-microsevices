package com.example.auth_Service.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails user = User.builder()
                .username("rutwik")
                .password(passwordEncoder.encode("abc"))
                .roles("ADMIN")   // ROLE_ADMIN
                .build();

        UserDetails user1 = User.builder()
                .username("shyam").password(passwordEncoder.encode("abc")).roles("ADMIN") .build();

        UserDetails user2 = User.builder()
                .username("ram").password(passwordEncoder.encode("abc")).roles("user").build();

        return new InMemoryUserDetailsManager(user,user1,user2);
    }
}
