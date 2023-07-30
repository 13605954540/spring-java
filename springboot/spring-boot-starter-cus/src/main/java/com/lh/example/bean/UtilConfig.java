package com.lh.example.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(SimpleBean.class)
@Configuration
public class UtilConfig {

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean().setId(1).setName("1");
    }
}
