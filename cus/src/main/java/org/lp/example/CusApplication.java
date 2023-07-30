package org.lp.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@SpringBootApplication
public class CusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CusApplication.class,args);
    }
}
