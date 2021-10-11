package com.spring.test.springtest.test2;//package com.spring.test.sourcetest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

///**
// * @author brink
// * 2021/7/6 11:14
// */
@Controller
@RequestMapping("/test")
public class TestScopeController {
    @Autowired
    private ApplicationContext context;
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public String test1() {
        System.out.println("这是一条狗："+context.getBean(Dog.class));
        System.out.println("这是一只猪："+context.getBean(Pig.class));
        return "这是一个测试作用域";
    }
}
