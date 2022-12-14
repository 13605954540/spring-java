package com.lp.first.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LP
 * @date 2018/9/8
 */
@Controller
public class ThymeleafTestContoller {

    @RequestMapping("/thymeleafTest")
    public ModelAndView thymeleafTest() {
        List<Map>list = new ArrayList<>();
        Map map1 = new HashMap();
        map1.put("value","苹果");
        Map map2 = new HashMap();
        map2.put("value","香蕉");
        Map map3 = new HashMap();
        map3.put("value","西瓜");
        Map map4 = new HashMap();
        map4.put("value","香蕉");
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("thymeleaf");
        return modelAndView;
    }
}
