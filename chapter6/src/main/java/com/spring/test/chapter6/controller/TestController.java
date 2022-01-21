package com.spring.test.chapter6.controller;

import com.spring.test.chapter6.chapter6.User;
import com.spring.test.chapter6.chapter6.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author brink
 * 2021/7/6 11:14
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public String test1() {
        User user = new User();
        user.setCount(100);
        user.setUserName("第一个");
        userService.insertUser(user);
        return "这是测试1";
    }
}
