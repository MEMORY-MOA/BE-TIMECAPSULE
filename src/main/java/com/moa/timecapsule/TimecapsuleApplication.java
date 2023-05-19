package com.moa.timecapsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TimecapsuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimecapsuleApplication.class, args);
	}

}
