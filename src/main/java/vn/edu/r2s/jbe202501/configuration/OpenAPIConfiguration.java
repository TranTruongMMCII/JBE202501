package vn.edu.r2s.jbe202501.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("Demo JBE-01-2025 API")
				.contact(new Contact().name("Truong").email("truong@gmail.com"))
				.version("1.0.0")
				.description("This is a sample Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
	}

}