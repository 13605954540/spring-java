package org.example.controller;

import org.example.bean.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/server-test")
public class ServerTestController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private Man man;

    @GetMapping("/go")
    private ResponseEntity<String> test() {
        for(int i = 0; i < 20; i++) {
            ResponseEntity<String> res = restTemplate.getForEntity("http://spring-cloud-provider/provider-test/go", String.class);
            System.err.println(res.getBody());
        }
        return null;
    }
}
