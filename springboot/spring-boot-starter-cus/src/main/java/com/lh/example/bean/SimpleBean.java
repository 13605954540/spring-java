package com.lh.example.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "simplebean")
public class SimpleBean {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public SimpleBean setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SimpleBean setName(String name) {
        this.name = name;
        return this;
    }
}
