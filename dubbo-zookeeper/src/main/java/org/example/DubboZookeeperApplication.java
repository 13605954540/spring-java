package org.example;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan
@EnableDubbo
@EnableDubboConfig
public class DubboZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboZookeeperApplication.class,args);
    }
}
