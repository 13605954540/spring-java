package com.lp.first.learn.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author LP
 * @date 2018/9/5
 */
@Component
@ConfigurationProperties(prefix = "beach")
@Data
public class Beach {

    private String id;

    private String name;

    private Integer age;

    private Map<String,Object> map;

    private List<Object> list;

    private Dog dog;

    @Override
    public String toString() {
        return "Beach{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }
}
