package com.example.jarinara_back.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECONDS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		// 모든 경로에 대해
		registry.addMapping("/**")
			//Origin이 http:localhost:3000에 대해
			.allowedOrigins("http://localhost:3000")
			// GET, POST, PUT, PATCH, DELETE, OPSIONS 메서드를 허용
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true) // 쿠키 요청 허용 여부
			.maxAge(MAX_AGE_SECONDS);
	}
}
