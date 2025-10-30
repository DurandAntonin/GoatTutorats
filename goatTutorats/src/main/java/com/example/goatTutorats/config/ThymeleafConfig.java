package com.example.goatTutorats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring6.ISpringTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Configure thymeleaf
 */
@Configuration
public class ThymeleafConfig {

    /**
     * Configure thymeleaf template engine.
     * Inject Time module in thymeleaf templates
     * @param templateResolver the template resolver to use, it's automatically configured by Spring Boot
     * @return the template engine
     */
    @Bean
    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        return engine;
    }
}
