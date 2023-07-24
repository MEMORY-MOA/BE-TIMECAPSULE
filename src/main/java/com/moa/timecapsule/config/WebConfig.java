package com.moa.timecapsule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:5173", "http://127.0.0.1:5173")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
			.allowCredentials(true)
			.exposedHeaders("*");
	}
}
