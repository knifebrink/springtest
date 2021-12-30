package com.spring.test.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Controller
@RequestMapping("hello")
public class HelloController {


    @RequestMapping("hello")
    @ResponseBody// 可以直接返回字符串
    public String testS( ){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        User user = (User) auth.getPrincipal();
        System.out.println("这是一个ctx "+ctx.hashCode());
        System.out.println("这是一个测试"+user);
        System.out.println("装载后 "+Thread.currentThread());

        return "你对我说：hello";
    }

    /**
     * 获取请求头
     */
    @RequestMapping("hello2")
    @ResponseBody// 可以直接返回字符串
    public String hhhh(HttpServletRequest httpServletRequest, @RequestHeader Map<String,String> map, HttpSession httpSession){
//        for(;;){
//            if(!httpServletRequest.getHeaderNames().hasMoreElements())
//                break ;
        Enumeration<String> aa= httpServletRequest.getHeaderNames();
        StrictHttpFirewall strictHttpFirewall;
        System.out.println( aa);
        System.out.println(        httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY));
;
        for(String key :map.keySet()){
            System.out.println(" "+key+": "+map.get(key));
        }


//        }

        return "hhh";
    }

    @RequestMapping(path = "hello3",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fun(){
        return "hello3";
    }

}
