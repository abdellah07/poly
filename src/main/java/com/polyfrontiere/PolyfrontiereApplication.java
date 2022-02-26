package com.polyfrontiere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PolyfrontiereApplication {
	public static void main(String[] args) {
		SpringApplication.run(PolyfrontiereApplication.class, args);
	}
}
