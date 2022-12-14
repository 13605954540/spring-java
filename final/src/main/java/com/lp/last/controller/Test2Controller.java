package com.lp.last.controller;

import com.lp.last.bean.Test;
import com.lp.last.bean.Test2;
import com.lp.last.framework.base.BaseController;
import com.lp.last.service.Test2Service;
import com.lp.last.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苹神
 * @date 2022/12/13
 */
@Api("课程反馈表 控制层")
@CrossOrigin
@RestController
@RequestMapping("/test2")
public class Test2Controller extends BaseController<Test2, Test2Service> {
}
