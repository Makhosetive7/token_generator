package com.example.ElectricityTokenGenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        .requestMatchers("/api/users/allUsers").permitAll()
                        .requestMatchers("/api/users/{id}").permitAll()
                        .requestMatchers("/api/tokens/").permitAll()
                        .requestMatchers("/api/tokens/{id}").permitAll()
                        .requestMatchers("/api/tokens/generateToken/").permitAll()
                        .requestMatchers("/api/tokens/TokenTransfer").permitAll()
                        .requestMatchers("/api/tokens/createDonation").permitAll()
                        .requestMatchers("api/tokens/localVendor/purchase").permitAll()
                        .requestMatchers("/api/users/{id}").permitAll()
                        .requestMatchers("/api/tokens/delete/{id}").permitAll()
                        .anyRequest().authenticated())
                .httpBasic();

        return http.build();
    }
}
