package com.lp.last;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lp.last.*"})
public class FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

/*	@Bean
	public RedisTemplate redisTemplate() {
		return new RedisTemplate();
	}*/
}
