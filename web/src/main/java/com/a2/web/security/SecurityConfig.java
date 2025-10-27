package com.a2.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http
            .requiresChannel(channel -> channel.anyRequest().requiresSecure())  // HTTPS required
            .sessionManagement(c ->
                    c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // Stateless
            .csrf(AbstractHttpConfigurer::disable)                              // Dusabke CSRF
            .authorizeHttpRequests(auth -> auth 
               .requestMatchers("/login.html", "/semesterSelection.html", "/courseSelection.html", "/courseRegistrations.html","/form.css").permitAll()             // Allowed for public access 
                
               .requestMatchers("/api/login").permitAll()   // Allow public access 
              .requestMatchers(HttpMethod.POST,"/api/validate").permitAll()   // Allow public access 

                .requestMatchers("/api/refresh").permitAll()   // Allow public access 
                .requestMatchers("/student/add").permitAll()   // Allow public access

               //.requestMatchers(HttpMethod.POST, "/api/ login").permitAll()   // Allow POST public access 
             //  .requestMatchers(HttpMethod.POST, "/api/validate").permitAll()   // Allow POST public access
               //.anyRequest().authenticated()                                              // All other endpoints require authentication
               .anyRequest().authenticated()
               );

            // Adding the filter 
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
