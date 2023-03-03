package org.example.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.K;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.entity.User;
import org.example.service.ProviderService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

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

    /**
     * @param content
     */
    @KafkaListener(topics = "test")
    public String processGatewayFlowControlCacheEvictMessage(String content) {
        System.err.println("进入");
        System.err.println(content);
        return "消息还给你了哦";
//        log.info("接收到 GatewayFlowControlCacheEvictMessage, msg:{}", content);
//        GatewayFlowControlCacheEvictMessage message = Jsons.toObject(content, GatewayFlowControlCacheEvictMessage.class);
//        for (String serviceId : message.getServiceId()) {
//            log.info("调用清除缓存 serviceId:{}", serviceId);
//            evictCache(serviceId);
//        }
    }
}
