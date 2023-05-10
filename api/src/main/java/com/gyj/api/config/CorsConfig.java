package com.gyj.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 允许跨域请求的来源地址
     */
    private final String[] allowedOrigins = {"http://localhost:8081"," http://localhost:8080"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置允许跨域请求的路径和方法
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
