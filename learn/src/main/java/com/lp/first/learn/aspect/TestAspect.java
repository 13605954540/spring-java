package com.lp.first.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author LP
 * @date 2018/8/10
 */
@Aspect
@Component
public class TestAspect {

    @Pointcut(value = "execution(* lp(..))")
    public void point() {

    }

    @Before("point()")
    public void doBefore(JoinPoint joinPoint) {
//        System.err.println("--------------- 进入切面1 ----------------");
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        MyTest test = method.getAnnotation(MyTest.class);
//        if (test == null) {
//            throw new RuntimeException("不存在这个注解,退出");
//        } else {
//            System.err.println("---------------" + test.value() + "----------------");
//        }
//        Test obj = (Test)jp.getArgs()[0];
//        obj.setId("123");
//        System.out.println("=========执行前置通知==========");
    }

    @After("point()")
    public void doAfter(JoinPoint jp) {
//        System.out.println("=========执行后置通知==========");
    }

    @AfterThrowing(pointcut = "point()",throwing = "ex")
    public void test(Throwable ex) {
//        System.err.println("------------------- 异常通知前奏 --------------------");
//        System.err.println(ex);
//        System.err.println("------------------- 异常通知 ----------------------");
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        System.err.println(response.getContentType());
//        Integer status = response.getStatus();
//        Map<String,Object> bodyMap = new HashMap<>();
//        bodyMap.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        bodyMap.put("status",status);
    }

    @AfterReturning(pointcut = "point()",returning = "returnValue")
    public void doReturn(Object returnValue) {
//        System.err.println("------------------- doReturn ----------------------");
//        System.err.println(returnValue);
//        ResponseEntity responseEntity = new ResponseEntity();
    }

//    public String getErrorType(Integer status) {
//
//    }
}
