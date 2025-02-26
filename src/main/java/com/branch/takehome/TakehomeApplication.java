package com.branch.takehome;

import java.util.TreeMap;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@ComponentScan("com.branch.takehome")
public class TakehomeApplication {
	
	@Autowired(required = false)
	private BuildProperties buildProperties;

	public static void main(String[] args) {
		SpringApplication.run(TakehomeApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi() {
		//Add custom info to swagger docs 
		
		String description = "Take home test";
		
		return new OpenAPI().info(
				new Info()
					.title(description)
					.version(buildProperties != null ? buildProperties.getVersion() : "localdev")
					.description(description)
					.contact(new Contact().email("daviddonatucci056@gmail.com"))
				);
	}
	
	@Bean
	public OpenApiCustomizer customApi() {
		//Ensure in alphabetical order
		return openApi -> openApi.getComponents().setSchemas(new TreeMap<>(openApi.getComponents().getSchemas()));
	}
}
