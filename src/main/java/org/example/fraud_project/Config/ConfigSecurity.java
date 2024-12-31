package org.example.fraud_project.Config;




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

    // This bean defines the security filter chain, which contains the security configurations.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers( "/register").permitAll()
                        .requestMatchers("/login").permitAll() // Allow public access to login and register endpoints.
                        .anyRequest().authenticated()  // Require authentication for all other requests.
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)  // Redirect to /transaction after successful login.
                )
                .logout(config -> config
                        .logoutSuccessUrl("/login")  // Redirect to /login after logout.
                )
                .build();
    }

    // Bean for PasswordEncoder, required for encoding passwords (e.g., BCrypt).
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
