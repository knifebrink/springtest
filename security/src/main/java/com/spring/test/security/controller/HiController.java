package com.spring.test.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("hi")
public class HiController {


    @RequestMapping("hi")
    @ResponseBody// 可以直接返回字符串
    public String test1( ){
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            User user = (User) auth.getPrincipal();
            System.out.println(user);
        }catch (Exception e){e.printStackTrace();}

        return "你对我说：hi";
    }


}
