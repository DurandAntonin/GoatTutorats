package com.example.goatTutorats.config;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Configure swagger for the application.
 * Configure swagger paths for example.
 */
@Component
public class SwaggerConfiguration implements ApplicationListener<ApplicationPreparedEvent> {

    private final String baseDocUrl = "/docs";

    /**
     * Configure swagger like swagger endpoints.
     * @param event application prepared event.
     */
    @Override
    public void onApplicationEvent(final ApplicationPreparedEvent event) {
        ConfigurableEnvironment environment = event.getApplicationContext().getEnvironment();
        Properties props = new Properties();
        props.put("springdoc.swagger-ui.path", this.swaggerUIPath());
        props.put("springdoc.api-docs.path", this.swaggerPath());
        environment.getPropertySources()
                .addFirst(new PropertiesPropertySource("programmatically", props));
    }

    /**
     * Define swagger ui path.
     * @return swagger ui path.
     */
    private String swaggerUIPath() {
        return String.format("%s/ui-doc", this.baseDocUrl);
    }

    /**
     * Define swagger doc path.
     * @return swagger doc path.
     */
    private String swaggerPath() {
        return String.format("%s/doc", this.baseDocUrl);
    }
}