package com.example.potato_tuto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Potato CRUD API")
                .version("1.0.0")
                .description("User, Post API Documentation with Swagger")
                .contact(new Contact()
                        .name("최규성")
                        .email("clitico12@gmail.com")));
    }
}