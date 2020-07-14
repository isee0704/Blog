package com.shyoc.config;

import com.shyoc.interceptor.AuthorizationInterceptor;
import com.shyoc.interceptor.UVStatisticInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(uvStatisticInterceptor())
                .addPathPatterns("/**");
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    @Bean
    public UVStatisticInterceptor uvStatisticInterceptor() {
        return new UVStatisticInterceptor();
    }
}
