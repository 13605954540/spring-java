package org.example;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.controller.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);

        NettyServer nettyServer =new  NettyServer ();
        nettyServer.start();
        System.err.println("======服务已经启动========");
    }
}
