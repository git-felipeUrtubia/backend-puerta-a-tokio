package com.example.api_gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${AUTH_SERVICE_URI:http://localhost:8080}")
    private String authServiceUri;

    @Value("${USER_SERVICE_URI:http://localhost:8080}")
    private String userServiceUri;

    @Value("${CATALOG_SERVICE_URI:http://localhost:8080}")
    private String catalogServiceUri;

    @Value("${COMMENT_SERVICE_URI:http://localhost:8080}")
    private String commentServiceUri;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("catalog-service", r -> r.path("/catalog/**")
                        .uri(catalogServiceUri))

                .route("auth-service", r -> r.path("/auth/**")
                        .uri("http://auth-service:8080"))

                .route("user-service", r -> r.path("/user/**")
                        .uri("http://user-service:8080"))

                .route("comments-service", r -> r.path("/comment/**")
                        .uri(commentServiceUri))

                .build();
    }

}
