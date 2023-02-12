package org.example.controller;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/consumer-test")
public class ConsumerTestController {

    @Autowired
    public RestTemplate restTemplate;

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

    public static void main(String[] args) {
        int n = 16;
        String a = "懂";
        int hashValue = hash(a);
        System.err.println(hash(a));
        Map<String, Integer> map = new HashMap<>();
        map.put("6", 90);
        System.err.println(map.put("6", 101111));
        int b = 8;
        int c = 256;
        System.err.println(hashValue % 16);
        System.err.println((16 - 1) & hashValue);
        char [] cc = new char[8];
        System.err.println("cc: " + cc[5]);
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
