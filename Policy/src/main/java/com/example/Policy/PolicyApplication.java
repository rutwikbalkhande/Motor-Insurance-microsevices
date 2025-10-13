package com.example.Policy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PolicyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PolicyApplication.class, args);
		System.out.println("started......");
	}

}
