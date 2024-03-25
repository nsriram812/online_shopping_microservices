package com.microservices.discoveryserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().ignoringRequestMatchers("/eureka/**").and().authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
        return httpSecurity.build();
    }*/
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**"))
            .authorizeRequests(authorize -> authorize
                    .anyRequest().authenticated())
            .httpBasic(withDefaults());

    return http.build();
}
}