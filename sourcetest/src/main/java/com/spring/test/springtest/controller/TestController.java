package com.spring.test.springtest.controller;

import com.spring.test.springtest.controller.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Date;

/**
 * @author brink
 * 2021/7/6 11:14
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public String test1() {
        User user = new User();
        user.setCount(100);
        user.setUserName("第一个");
        System.out.println("this:"+this);
        return "这是测试1";
    }

    // 有问题
    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public ModelAndView test2(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    // 有问题
    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public ModelAndView test3(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index22");
        return modelAndView;
    }

    // 改用templates吧
    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(){
        return "xxx";
    }

    // 返回json数据
    @RequestMapping(value = "/testjson", method = RequestMethod.GET)
    public ModelAndView testjson(){
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(jsonView);
        modelAndView.addObject(new User("这是一个json测试",1));
        return modelAndView;
    }

    // 接受一定格式的参数
    @RequestMapping(value = "/test5", method = RequestMethod.GET)
    @ResponseBody
    public String test5(@DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date date){

        return date.toString();
    }
}
