package org.example;

import org.example.bean.Man;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server-test")
public class ServerTestController {

    @Autowired
    private Man man;

    @GetMapping("/go")
    private String test() {
        return "name: " + man.getName() + "   age: " + man.getAge();
    }
}
