package org.example.config;

import com.alibaba.nacos.common.utils.HttpMethod;
import com.alibaba.nacos.shaded.com.google.common.net.HttpHeaders;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("path_route", r -> r.path("/csdn").filters(gatewayFilterSpec -> {
                            return gatewayFilterSpec.stripPrefix(1);
                        })
                        .uri("https://www.baidu.com"))
                .build();
    }

/*    @Bean
    public RouteLocatorBuilder routeLocatorBuilder(ConfigurableApplicationContext context) {
        return new RouteLocatorBuilder(context);
    }*/

//    @Bean
//    public RouteLocatorBuilder routeLocatorBuilder() {
//        return new RouteLocatorBuilder();
//    }

/*    HystrixObservableCommand.Setter getSetter() {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("group-accept");
        HystrixObservableCommand.Setter setter = HystrixObservableCommand.Setter.withGroupKey(groupKey);
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("command-accept");

        setter.andCommandKey(commandKey);

        HystrixCommandProperties.Setter proertiesSetter = HystrixCommandProperties.Setter();
        proertiesSetter
                *//* *
                 * 线程策略配置
                 *//*
                //设置线程模式 缺省 1000ms
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                //执行是否启用超时时间 缺省 true
                .withExecutionTimeoutEnabled(true)
                //使用线程隔离时，是否对命令执行超时的线程调用中断 缺省false
                .withExecutionIsolationThreadInterruptOnFutureCancel(false)
                //执行超时的时候是否要它中断 缺省 true
                .withExecutionIsolationThreadInterruptOnTimeout(true)
                //执行的超时时间 缺省 1000ms
                .withExecutionTimeoutInMilliseconds(2000)
                *//* *
                 * 熔断策略
                 *//*
                //是否开启溶断 缺省 true
                .withCircuitBreakerEnabled(true)
                // 是否允许熔断器忽略错误,默认false, 不开启 ；
                // true，断路器强制进入“关闭”状态，它会接收所有请求。
                // 如果forceOpen属性为true，该属性不生效
                .withCircuitBreakerForceClosed(false)
                // 是否强制开启熔断器阻断所有请求, 默认为false
                // 为true时，所有请求都将被拒绝，直接到fallback.
                // 如果该属性设置为true，断路器将强制进入“打开”状态，
                // 它会拒绝所有请求。该属性优于forceClosed属性
                .withCircuitBreakerForceOpen(false)
                // 用来设置当断路器打开之后的休眠时间窗。
                // 休眠时间窗结束之后，会将断路器设置为“半开”状态，尝试熔断的请求命令，
                // 如果依然请求错误就将断路器继续设置为“打开”状态，如果成功，就设置为“关闭”状态
                // 熔断器默认工作时间,默认:5000豪秒.
                // 熔断器中断请求10秒后会进入半打开状态,放部分流量过去重试.
                .withCircuitBreakerSleepWindowInMilliseconds(5000)
                // 熔断器在整个统计时间内是否开启的阀值.
                // 在metricsRollingStatisticalWindowInMilliseconds（默认10s）内默认至少请求10次，
                // 熔断器才发挥起作用,9次熔断器都不起作用。
                .withCircuitBreakerRequestVolumeThreshold(100)
                // 该属性用来设置断路器打开的错误百分比条件。默认值为50.
                // 表示在滚动时间窗中，在请求值超过requestVolumeThreshold阈值的前提下，
                // 如果错误请求数百分比超过50，就把断路器设置为“打开”状态，否则就设置为“关闭”状态
                .withCircuitBreakerErrorThresholdPercentage(50);

        setter.andCommandPropertiesDefaults(proertiesSetter);

        return setter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();
        RouteLocatorBuilder.Builder serviceProvider = routes
                .route("accept",
                        r -> r.method(HttpMethod.GET)
                                .and()
                                .path("/gateway-accept/**")
                                .and()
                                .header(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
                                .filters(f -> {
                                    f.rewritePath("/gateway-accept/(?<path>.*)", "/${path}");
                                    f.requestRateLimiter(
                                            config -> config.setKeyResolver(new GenericAccessResolver())
                                                    .setRateLimiter(redisRateLimiter()));
                                    f.hystrix(config -> config.setName("accept")
                                            .setFallbackUri("forward:/gateway-fallback")
                                            .setSetter(getSetter()));
                                    return f;
                                })
                                .uri("http://localhost:8888")
                );

        return serviceProvider.build();
    }*/
}
