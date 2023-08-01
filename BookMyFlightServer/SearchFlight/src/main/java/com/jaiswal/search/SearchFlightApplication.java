package com.jaiswal.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SearchFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchFlightApplication.class, args);
	}

}
