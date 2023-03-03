package org.example.controller;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.bean.ResponseResult;
import org.example.util.NettyClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@CrossOrigin
@RestController
@RequestMapping("/consumer-test")
public class ConsumerTestController {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public KafkaTemplate kafkaTemplate;

    //暂时注释 并移除包引入 因为会导致负载均衡失效
/*    @DubboReference(version = "2.0.0")
    public ProviderService providerService;*/

    @GetMapping("/go")
    public ResponseEntity<String> test() {
        for(int i = 0; i < 20; i++) {
            ResponseEntity<String> res = restTemplate.getForEntity("http://spring-cloud-provider/provider-test/go", String.class);
            System.err.println(res.getBody());
        }
        return null;
    }

/*    @GetMapping("/go2")
    public String go2() {
        return providerService.getResult("yoyoyo!");
    }*/

    @GetMapping("/go3")
    public Object go3() {
        // 引用远程服务
        // 该实例很重量，里面封装了所有与注册中心及服务提供方连接，请缓存
        ReferenceConfig<GenericService> config = new ReferenceConfig<>();
        // 弱类型接口名
        config.setInterface("org.example.service.ProviderService");
        config.setVersion("2.0.0");
        // 声明为泛化接口
        config.setGeneric(true);
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        //缓存 优化泛化调用性能
        GenericService genericService = cache.get(config);
//        GenericService genericService = reference.get();
        String param = "小花";
        // 如果返回POJO将自动转成Map
        Object result = genericService.$invoke("getUser", new String[]{"java.lang.String"}, new Object[]{param});
        return result;
    }

    /**
     * netty 请求
     *
     * @param msg
     * @return
     */
    @GetMapping("/helloNetty")
    public ResponseResult helloNetty(@RequestParam String msg) {
        return NettyClientUtils.helloNetty(msg);
    }

    /**
     * kafka消费
     *
     * @return
     */
    @GetMapping("/kafka")
    public ResponseResult kafka() throws ExecutionException, InterruptedException {
        Future<SendResult> val = kafkaTemplate.send(new ProducerRecord("test", "new的go"));
        SendResult sendResult = val.get();
        System.err.println("--------------------- " + sendResult.toString() + "  ------------------------");
        return ResponseResult.ok();
    }
}
