package com.jaiswal.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class BookFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookFlightApplication.class, args);
	}

}
