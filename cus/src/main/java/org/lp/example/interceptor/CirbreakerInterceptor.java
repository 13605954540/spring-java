package org.lp.example.interceptor;

import org.lp.example.cusinterface.CircuitBreaker;
import org.lp.example.servicegovernance.CircuitBreakerDone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CirbreakerInterceptor implements HandlerInterceptor {

    @Autowired
    public RedisTemplate redisTemplate;

    private String name;

    private CircuitBreakerDone done;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            CircuitBreaker circuitBreaker = handlerMethod.getMethodAnnotation(CircuitBreaker.class);
            if(circuitBreaker == null) {
                return true;
            }
            String name = circuitBreaker.name();
            Integer maxFailures = circuitBreaker.maxFailures();
            long timeOut = circuitBreaker.timeOut();
            Object obj = redisTemplate.opsForValue().get(name);
            CircuitBreakerDone circuitBreakerDone = obj == null ? new CircuitBreakerDone(maxFailures) : (CircuitBreakerDone)obj;
            this.name = name;
            this.done = circuitBreakerDone;
            redisTemplate.opsForValue().set(name, circuitBreakerDone);
            if(circuitBreakerDone != null && !circuitBreakerDone.isDoneBefore()) {
                System.err.println("处于熔断状态，请求失败！");
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        Integer status = response.getStatus();
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(status != 200 && this.done != null) {
            this.done.afterDone(response.getStatus());
            redisTemplate.opsForValue().set(this.name, this.done);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {

    }
}
