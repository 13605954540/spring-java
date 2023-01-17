package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/consumer-test")
public class ConsumerTestController {

    @Value("coupon.user")
    private Object user;

    @GetMapping("/go")
    private Object update() {
        return user;
    }
}
