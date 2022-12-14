package com.lp.first.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author LP
 * @date 2018/8/10
 */
@Aspect
@Component
public class UpdateAspect {

    @Pointcut(value = "execution(* go(..))")
    public void point() {

    }

    @Before("point()")
    public void doBefore(JoinPoint jp) {
//        Test obj = (Test)jp.getArgs()[0];
//        obj.setId("123");
//        System.out.println("=========执行前置通知==========");
    }

    @After("point()")
    public void doAfter(JoinPoint jp) {
//        System.out.println("=========执行后置通知==========");
    }
}
