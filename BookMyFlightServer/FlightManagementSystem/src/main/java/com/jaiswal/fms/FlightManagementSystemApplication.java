package com.jaiswal.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FlightManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementSystemApplication.class, args);
	}

}
