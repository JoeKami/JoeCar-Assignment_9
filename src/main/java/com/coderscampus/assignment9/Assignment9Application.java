package com.coderscampus.assignment9;

import com.coderscampus.assignment9.repository.InMemoryRecipeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootApplication
public class Assignment9Application {
	public static void main(String[] args) throws IOException {SpringApplication.run(Assignment9Application.class, args);
	}
}
