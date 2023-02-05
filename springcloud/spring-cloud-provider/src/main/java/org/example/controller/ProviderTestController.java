package org.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider-test")
public class ProviderTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/go")
    private String test() {
        return "port: " + port;
    }
}
