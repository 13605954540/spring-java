package com.lp.first.learn.old.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.lp.first.learn.LearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author LP
 * @date 2019/3/30
 */
@SpringBootTest(classes = LearnApplication.class)
@RunWith(SpringRunner.class)
public class Reference {

//    @Test
//    public static void main(String[] args) {
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
//        reference.setApplication(new ApplicationConfig("consumer"));
//        reference.setInterface("com.lp.first.basic.test.provider.TestProvider");
//        reference.setProtocol("dubbo");
//        reference.setTimeout(20000);
//        reference.setVersion("1.0");
//        reference.setGroup("group");
//        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1");
//        registryConfig.setPort(2182);
//        reference.setRegistry(registryConfig);
//        GenericService genericService = reference.get();
//        Object result = genericService.$invoke("dubboTest",new String[] {"java.lang.String","java.lang.Integer"},new Object[]{"test",1});
//        System.err.println(result);
//    }

    @Test
    public void hh() {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("lp-basic"));
        reference.setInterface("com.lp.first.basic.test.provider.TestProvider");
        reference.setProtocol("dubbo");
        reference.setTimeout(2000);
//        reference.setVersion("1.0");
//        reference.setGroup("group");
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://127.0.0.1:2182");
        reference.setGeneric(true);
//        registryConfig.setPort(2182);
//        reference.setGeneric(true);
        reference.setRegistry(registryConfig);
        GenericService genericService = reference.get();
        Object result = genericService.$invoke("dubboTest",new String[] {"java.lang.String","java.lang.Integer"},new Object[]{"test",1});
        System.err.println(result);
    }

//    public static void main(String[] args) {
//        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
//        reference.setApplication(new ApplicationConfig("consumer"));
//        reference.setInterface("service");
//        reference.setProtocol("dubbo");
//        reference.setTimeout(10000);
//        reference.setVersion("1.0");
//reference.setGroup("group");
//        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1"));
//        reference.setGeneric(true);
//        GenericService genericService = reference.get();
//        try {
////        Object result = genericService.$invoke("method", new String[] { "java.lang.String", "java.lang.Integer" },
////    new Object[] { "abc", 0 }); 
//        }
//    }
}
