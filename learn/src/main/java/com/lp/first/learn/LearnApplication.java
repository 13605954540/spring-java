package com.lp.first.learn;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * @author LP
 * @date 2018/5/1
 */
@ComponentScan(basePackages = {"com.lp.first.learn.*"})
@SpringBootApplication
//@EnableDubbo
//@EnableEurekaClient
public class LearnApplication {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(LearnApplication.class, args);
    }
}
