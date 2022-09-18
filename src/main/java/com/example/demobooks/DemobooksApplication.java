package com.example.demobooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DemobooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemobooksApplication.class, args);
	}

}
