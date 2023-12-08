package com.qgsoftware.lastdevonearth.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Configuration
	public static class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://localhost:4200")
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("*")
					.allowCredentials(true);
		}
	}

}
