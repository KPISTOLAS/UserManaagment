package com.project.Company.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration // This class configures our security settings—protecting our app like a security guard
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Strong password encryption, because weak passwords are a no-go
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF protection is disabled for simplicity (be cautious in production)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all requests—this is where you’d normally secure endpoints
                );

        return http.build(); // Build and return the security configuration
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000")); // Allow frontend apps to talk to us
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Specify which HTTP methods we allow
        configuration.setAllowedHeaders(Arrays.asList("*")); // Accept all headers (you’re free to send anything)
        configuration.setAllowCredentials(true); // Allow credentials like cookies (because we trust our clients)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply this CORS config to all API endpoints
        return source; // Return the CORS configuration source
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/styles.css", "/script2.js", "/images/**", "/static/**"); // Don’t bother securing static assets (they’re public!)
    }
}
