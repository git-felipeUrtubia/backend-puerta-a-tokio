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
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

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

                        .pathMatchers(
                                "/auth/register",
                                "/auth/login",
                                "/user/find-user-{id}",
                                "/user/save-user" ,
                                "/catalog/tour/find-all",
                                "/comment/save-comment",
                                "/comment/find-all",
                                "/catalog/galery/create",
                                "/catalog/tour/create")
                        .permitAll()

                        .pathMatchers(
                                "/auth/saludo",
                                "/user/find-all-users").authenticated()

                        .anyExchange().denyAll()
                )
                .addFilterBefore(new JwtTokenValidator(jwtUtils), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public CorsWebFilter CorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("https://puerta-a-tokio.vercel.app");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return  new CorsWebFilter(source);
    }


}
