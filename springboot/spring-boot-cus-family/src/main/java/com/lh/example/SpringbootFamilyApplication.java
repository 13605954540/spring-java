package com.lh.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootFamilyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootFamilyApplication.class,args);
    }
}
