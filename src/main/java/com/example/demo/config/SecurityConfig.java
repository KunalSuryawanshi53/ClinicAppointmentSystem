package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Authorization
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )

                // Login configuration
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                // Logout configuration
                .logout(logout -> logout
                        .logoutUrl("/logout")                         // logout endpoint (POST)
                        .logoutSuccessUrl("/login?logout=true")       // redirect after logout
                        .invalidateHttpSession(true)                  // destroy session
                        .deleteCookies("JSESSIONID")                  // remove cookies
                        .permitAll()
                )

                // Keep CSRF enabled (default behavior, no need to disable or enable explicitly)
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")); // optional if you use H2

        return http.build();
    }
}
