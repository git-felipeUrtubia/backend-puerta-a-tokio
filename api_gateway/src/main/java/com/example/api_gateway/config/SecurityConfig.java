package com.example.api_gateway.config;

import com.example.api_gateway.config.filter.JwtTokenValidator;
import com.example.api_gateway.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityWebFilterChain SecurityFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(http -> http

                        .pathMatchers(org.springframework.http.HttpMethod.OPTIONS).permitAll()
                        .pathMatchers(
                                "/auth/**",
                                "/user/find-user-{id}",
                                "/user/save-user/**" ,
                                "/catalog/tour/find-all/**",
                                "/catalog/tour/find-all",
                                "/comment/save-comment",
                                "/comment/find-all/**",
                                "/catalog/galery/create",
                                "/catalog/tour/create")
                        .permitAll()

                        .pathMatchers(
                                "/auth/saludo",
                                "/user/find-all-users").authenticated()

                        .anyExchange().denyAll()
                )
                .addFilterAt(new JwtTokenValidator(jwtUtils), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("https://puerta-a-tokio-front.vercel.app/home"));
        corsConfig.setMaxAge(3600L); // Cachear la respuesta de preflight 1 hora
        corsConfig.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, OPTIONS...)
        corsConfig.addAllowedHeader("*"); // Permitir todas las cabeceras
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }


}
