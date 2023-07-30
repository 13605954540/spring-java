package org.lp.example.controller;

import org.lp.example.cusinterface.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * 字符串类型（可以是数组 对象）
     *
     * @return
     */
    @GetMapping("/go")
    @CircuitBreaker(name = "test哦")
    public String str() {
        int num = (int)(Math.random()*100);
        System.err.println("num: " + num);
        if(num >= 20) {
            throw new RuntimeException("超过20咯");
        }
        return "gateway go";
    }
}
