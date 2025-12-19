package com.travelagent.authservice.securityConfigs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
       
        http.csrf(csrf->csrf.disable())
        .authorizeHttpRequests(auth->auth
            .requestMatchers("/auth/logout","/auth/validate").authenticated()
            .requestMatchers("/auth/**").permitAll()
            .requestMatchers("/health").permitAll()
            .requestMatchers("/user/signup").permitAll()
            .anyRequest().authenticated() 
        );

        
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    } 
}
