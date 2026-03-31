package com.dg.cy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("test-proj API")
                        .version("1.0.0")
                        .description("REST API documentation for Employee and Department management")
                        .contact(new Contact()
                                .name("dg-test-app")));
    }
}
