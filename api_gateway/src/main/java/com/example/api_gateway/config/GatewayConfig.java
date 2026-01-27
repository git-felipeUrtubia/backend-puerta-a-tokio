package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("catalog-service", r -> r.path("/catalog/**")
                        .uri("http://catalog-service:8080"))

                .route("auth-service", r -> r.path("/auth/**")
                        .uri("http://auth-service:8080"))

                .route("user-service", r -> r.path("/user/**")
                        .uri("http://user-service:8080"))

                .route("comments-service", r -> r.path("/comment/**")
                        .uri("http://comments-service:8080"))

                .build();
    }

}
