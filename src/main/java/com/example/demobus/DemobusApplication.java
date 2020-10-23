package com.example.demobus;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemobusApplication {

	@Bean
	public Function<String, String> uppercase() {
		return String::toUpperCase;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemobusApplication.class, args);
	}

}
