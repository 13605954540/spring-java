package org.example.service.impl;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.service.ConsumerService;

@DubboService
public class ConsumerServiceImpl implements ConsumerService {

    @Override
    public String getResult(String str) {
        return "调用者返回： " + str;
    }
}
