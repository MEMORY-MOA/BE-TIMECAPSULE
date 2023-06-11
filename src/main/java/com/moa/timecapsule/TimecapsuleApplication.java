package com.moa.timecapsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class TimecapsuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimecapsuleApplication.class, args);
	}

}
