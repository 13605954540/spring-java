package org.example.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "man")
@Data
@Accessors(chain = true)
public class Man implements Serializable {

    private String name;

    private Integer age;
}
