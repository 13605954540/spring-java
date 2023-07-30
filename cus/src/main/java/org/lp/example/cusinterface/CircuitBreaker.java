package org.lp.example.cusinterface;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CircuitBreaker {

    //熔断器名称
    String name();

    //熔断器允许最大失败请求次数
    int maxFailures() default 5;

    //熔断器超时等待时间
    long timeOut() default 500;
}
