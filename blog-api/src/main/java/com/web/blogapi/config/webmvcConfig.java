package com.web.blogapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webmvcConfig implements WebMvcConfigurer {
    //Cross mapping configuration
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Assume front-end server is running on port 8080
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }
}
