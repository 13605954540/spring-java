package com.lp.last.controller;

import com.lp.last.bean.Test;
import com.lp.last.framework.base.BaseController;
import com.lp.last.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 苹神
 * @date 2022/12/13
 */
@Api("课程反馈表 控制层")
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestController extends BaseController<Test, TestService> {

    @Autowired
    private TestService testService;
}
