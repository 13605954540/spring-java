package org.example.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@CrossOrigin
@RestController
@RequestMapping("/provider-test")
public class ConsumerTestController {

    @GetMapping("/go")
    private String update() {
        return "provider-test";
    }
}
