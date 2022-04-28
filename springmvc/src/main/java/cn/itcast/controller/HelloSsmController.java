package cn.itcast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author brink
 * 2021/9/2 12:53
 * 行吧，还是挺费劲的。
 * 但是还是搞完了一个简单的，ssm？算了，再搞个M进来
 */
@Controller
@RequestMapping("/test")
public class HelloSsmController {
    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1() {
        System.out.println("this:"+this);
        return "this is test 1 , 因为中文乱码~";
    }
}
