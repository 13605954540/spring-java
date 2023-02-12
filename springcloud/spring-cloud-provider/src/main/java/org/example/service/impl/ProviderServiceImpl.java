package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.entity.User;
import org.example.service.ProviderService;

@DubboService(version = "1.0.0")
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String getResult(String str) {
        return "提供者返回： " + str;
    }

    @Override
    public User getUser(String str) {
        return new User().setId("1").setName(str).setAge(18);
    }
}
