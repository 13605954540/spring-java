package com.lp.test.controller;

import com.lp.test.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

//    Logger logger = LoggerFactory.getLogger(SourceController.class);

    @RequestMapping("/source")
    public String doSource() {
        LogUtils.debug("小强");
        return "source";
    }
}
