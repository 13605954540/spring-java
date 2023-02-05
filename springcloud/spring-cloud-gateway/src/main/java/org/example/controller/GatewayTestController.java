package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gateway-test")
public class GatewayTestController {

    @GetMapping("/go")
    @SentinelResource(value = "go", blockHandler = "handlerException")
    private String test() {
        int num = (int)(Math.random()*100);
        System.err.println("num: " + num);
        if(num >= 20) {
            throw new RuntimeException("超过20咯");
        }
        return "gateway go";
    }

    public String handlerException(BlockException blockException) {
        return "进入异常";
    }
}
