package com.example.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication (exclude = {
		org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
})
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
		System.out.println("started User service........");
	}

}
