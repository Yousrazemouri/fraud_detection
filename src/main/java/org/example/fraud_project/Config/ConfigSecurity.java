package org.example.fraud_project.Config;

import org.example.fraud_project.service.AppuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

    @Autowired
    private AppuserService appuserService; // Inject your custom UserDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/predict") // Disable CSRF for /predict endpoint
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/login", "/login.css").permitAll() // Allow public access
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specify your custom login page
                        .defaultSuccessUrl("/", true) // Redirect after successful login
                        .failureUrl("/login?error=true") // Redirect after login failure
                        .permitAll()
                )
                .logout(config -> config
                        .logoutUrl("/perform_logout") // The URL to trigger logout
                        .logoutSuccessUrl("/login") // Redirect after logout
                        .deleteCookies("JSESSIONID") // Remove session cookies
                        .permitAll()
                )
                .userDetailsService(appuserService) // Use your custom UserDetailsService for authentication
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder for secure password comparison
    }
}
