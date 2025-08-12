package com.salahscheduler.salahscheduler;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
        
        http.cors(c ->
        c.configurationSource(corsConfigurationSource()))
        .csrf().disable()
        
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    	
        .authorizeHttpRequests(authz -> authz
        		.requestMatchers("/**").permitAll()    
        		.anyRequest().authenticated()
        );
        
        return http.build();
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	
    	CorsConfiguration configurate = new CorsConfiguration();
    	configurate.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:19006"));
    	configurate.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    	configurate.setAllowedHeaders(Arrays.asList("*"));
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	
    	source.registerCorsConfiguration("/**", configurate);
    	
        return source;
    }
    
}