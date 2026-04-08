package com.aguape.infrastructure.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Dashboard API", version = "1.0", description = "API Ágape Sistemas")
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)


public class OpenApiConfig {}

