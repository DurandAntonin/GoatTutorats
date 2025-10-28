package com.example.goatTutorats;

import com.example.goatTutorats.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoatTutoratsApplication {

	public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GoatTutoratsApplication.class);
        application.addListeners(new SwaggerConfiguration());
        application.run(args);
	}

}
