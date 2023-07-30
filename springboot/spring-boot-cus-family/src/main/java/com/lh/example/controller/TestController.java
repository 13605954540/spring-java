package com.lh.example.controller;

import com.lh.example.bean.SimpleBean;
import com.lh.example.bean.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苹神
 * @date 2022/12/13
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UtilService utilService;

    @GetMapping("/get")
    public SimpleBean get() {
        return utilService.get();
    }
}
