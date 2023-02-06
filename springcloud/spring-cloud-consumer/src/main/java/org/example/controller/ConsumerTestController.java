package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
@RequestMapping("/consumer-test")
public class ConsumerTestController {
    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/go")
    private ResponseEntity<String> test() {
        for(int i = 0; i < 20; i++) {
            ResponseEntity<String> res = restTemplate.getForEntity("http://spring-cloud-provider/provider-test/go", String.class);
            System.err.println(res.getBody());
        }
        return null;
    }
}
