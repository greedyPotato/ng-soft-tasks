package com.tasks.tasksandusers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tasks.tasksandusers.security.JwtRequestFilter;

@Configuration
public class JwtSecurityConfig {
    
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.cors().and().headers().frameOptions().disable().and()
                .csrf().disable()
                .authorizeHttpRequests().requestMatchers("/authenticate").permitAll() //,"/**","/h2/**
                .requestMatchers("/tasks","/task/cempleted","/task/cempleted/**","/comment","/comment/**")
                .hasRole("USER").requestMatchers("/user","/user/**","/task","/task/**","/**").hasRole("ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).build();
                
    }
}
