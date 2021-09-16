package com.spring.test.chapter6.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author brink
 * 2021/9/16 16:43
 */
@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/test1")
    public ModelAndView test1(){
        System.out.println("有进来");
        redisTemplate.opsForValue().set("key1222", "value2222");
        ModelAndView modelAndView = new ModelAndView("123123");
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        modelAndView.addObject("sdfsdf");
        modelAndView.setView(view);
        return modelAndView;
    }
}
