package com.grievance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // 👈 ADD THIS LINE
public class SmartgrievanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartgrievanceApplication.class, args);
	}
}
