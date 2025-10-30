package com.example.goatTutorats;

import com.example.goatTutorats.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for Goat Tutorats.
 */
@SpringBootApplication
public class GoatTutoratsApplication {

    /**
     * Main method to start the Spring Boot application.
     * @param args command-line arguments
     */
	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GoatTutoratsApplication.class);
        application.addListeners(new SwaggerConfiguration());
        application.run(args);
	}

}
