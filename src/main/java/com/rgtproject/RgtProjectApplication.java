package com.rgtproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RgtProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RgtProjectApplication.class, args);
	}

}
