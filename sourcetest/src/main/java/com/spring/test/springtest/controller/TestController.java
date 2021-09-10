package com.spring.test.springtest.controller;

import com.spring.test.springtest.controller.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;

import java.util.Date;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * @author brink
 * 2021/7/6 11:14
 * 主要是{@link DispatcherServlet}
 * 主要的流程确实如深入浅出springboot，所说那样，总结得很到位
 * 1. 从{@link DispatcherServlet 中心类，入口类},
 * 2. 根据请求从{@link HandlerMapping }列表
 * 中获取{@link HandlerExecutionChain 一个hander/contorller的封装类},
 * 如封装被{@link RequestMapping}修饰的{@link HandlerMethod}，
 * 3. 根据被执行的对象，选择合适的{@link HandlerAdapter}适配器进行执行对象，如{@link HttpRequestHandlerAdapter}，一般的http请求
 * 4. 执行得到{@link ModelAndView}数据视图结果
 * 5. 使用视图解析器{@link ViewResolver}进一步得到视图，包括定位资源，得到{@link View}，如果是jsonview，没有这一步
 * 6. 结合view和数据，用{@link HttpServletResponse}进行结果渲染到页面展示
 *
 *  spring 的ioc容器真的是贯穿全文，很喜欢用一个注册轮训的代码结构。将接口注册到类，类根据对象遍历接口看他们自身是否支持得出结果
 *  其二，适配器模式，就是适配器模式。
 *
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
