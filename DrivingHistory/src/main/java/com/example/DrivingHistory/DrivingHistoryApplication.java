package com.example.DrivingHistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DrivingHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrivingHistoryApplication.class, args);
		System.out.println("started DrivingHistory application.......");
	}

}
