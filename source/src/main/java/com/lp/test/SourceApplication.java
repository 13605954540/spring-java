package com.lp.test;

import com.lp.test.util.LogUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class,args);
        //控制台的请忽略 未调好 日志文件正常显示调用包的全类名和行数
        LogUtils.debug("name： {0}", "苹神");
    }
}
