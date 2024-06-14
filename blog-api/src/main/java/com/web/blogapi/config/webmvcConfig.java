package com.web.blogapi.config;

import com.web.blogapi.handler.loginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webmvcConfig implements WebMvcConfigurer {
    @Autowired
    loginInterceptor loginInterceptor;

    /**
     * Cross mapping configuration
     * @param registry Spring CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Assume front-end server is running on port 8080
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    /**
     * Interceptor configuration
     *     Currently contains: Login interceptor
     * @param registry Spring InterceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/logout")
                .addPathPatterns("/comment/delete")
                .addPathPatterns("/comment/publish")
                .addPathPatterns("/article/edit")
                .addPathPatterns("/article/publish");
    }
}
