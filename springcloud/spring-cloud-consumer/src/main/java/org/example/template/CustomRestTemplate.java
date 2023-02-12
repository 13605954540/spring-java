package org.example.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomRestTemplate {

    private static final String PREFIX = "http://";

    @Autowired
    public RestTemplate restTemplate;

    public <T>T getForEntity(String url) {
        ResponseEntity<T> res = (ResponseEntity<T>) restTemplate.getForEntity(PREFIX + url, Object.class);
        return res.getBody();
    }

    public <T>T postForEntity(String url, Object obj) {
        ResponseEntity<T> res = (ResponseEntity<T>) restTemplate.postForEntity(PREFIX + url, obj, Object.class);
        return res.getBody();
    }
}
