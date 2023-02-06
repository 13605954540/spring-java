package org.example.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * <pre>
 * 第一种方式：extends AbstractNameValueGatewayFilterFactory
 * 名称必须以GatewayFilterFactory为结尾，命名约束，否则找不到
 * </pre>
 */
@Component
//@Log
public class CustomGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        System.err.println("------------------------------");
        return ((exchange, chain) -> {
            System.err.println("请求进来了... " + config.getName() +"," + config.getValue());
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .build();
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(modifiedRequest)
                    .build();
            return chain.filter(modifiedExchange);
        });
    }
}
