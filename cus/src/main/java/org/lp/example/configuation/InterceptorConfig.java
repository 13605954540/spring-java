package org.lp.example.configuation;

import org.lp.example.interceptor.CirbreakerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(cirbreakerInterceptor());
        registration.addPathPatterns("/**");
    }

    @Bean
    public CirbreakerInterceptor cirbreakerInterceptor() {
        return new CirbreakerInterceptor();
    }
}
