package org.example.config;

import org.example.bean.ResponseResult;
import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public ResponseResult responseResult() {
        System.err.println("我实例化了哦");
        return new ResponseResult();
    }
}
