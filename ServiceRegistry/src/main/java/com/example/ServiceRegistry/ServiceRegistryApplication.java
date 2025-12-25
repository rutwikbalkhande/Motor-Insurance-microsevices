package com.example.ServiceRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	/*
	in other microservice register here as client  <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId> u
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
		System.out.println("started.......");


	}

}
