package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.entity.User;
import org.example.service.ProviderService;
import org.springframework.beans.factory.annotation.Value;

@DubboService(version = "2.0.0", loadbalance = "roundrobin")
public class ProviderServiceImpl2 implements ProviderService {

    @Value("${server.port}")
    private String port;

    @Override
    public String getResult(String str) {
        return "提供者返回： " + str + " port: " + port;
    }

    @Override
    public User getUser(String str) {
        return new User().setId(port).setName(str).setAge(28);
    }
}
