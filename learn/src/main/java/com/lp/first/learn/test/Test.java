//package com.lp.first.learn.test;
//
//import com.lp.first.learn.bean.Beach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @author LP
// * @date 2018/5/1
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Component
//public class Test {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Autowired
//    Beach beach;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @org.junit.Test
//    public void sys() {
//        String password = "123456";
//        String result = passwordEncoder.encode(password);
//        System.err.println(result);
//
//    }
//
//    public static void main(String[] args) {
////        Test test = new Test();
////        test.sys();
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        set.add(1);
//        System.err.println(set);
//    }
//}
