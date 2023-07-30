package org.example.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/gateway-test")
public class GatewayTestController {

    @GetMapping("/go")
//    @SentinelResource(value = "go", blockHandler = "handlerException")
    private String test() {
        int num = (int)(Math.random()*100);
        System.err.println("num: " + num);
        if(num >= 20) {
            throw new RuntimeException("超过20咯");
        }
        return "gateway go";
    }

    public static void main(String[] args) {
        System.err.println(getCurrentMinuteTimestamp(new Date().getTime()));
    }

    protected static Long getCurrentMinuteTimestamp(Long currentTimestamp) {
        return currentTimestamp / 1000L / 60L * 60L * 1000L;
    }

//    public String handlerException(BlockException blockException) {
//        return "进入异常";
//    }
}
