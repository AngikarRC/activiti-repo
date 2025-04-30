package com.activiti.activitisample.hiring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService  userDetailsService(){
        return username ->
                User.withUsername(username).password("{noop}password").roles(
                        "USER"
                ).build();
    }
}
