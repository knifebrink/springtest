package com.spring.test.springtest.aop;

import com.spring.test.springtest.User;
import com.spring.test.springtest.aop.service.UserService;
import com.spring.test.springtest.aop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author brink
 * 深入浅出的第四章
 * 2021/9/8 16:04
 */
@SpringBootApplication(scanBasePackages = {"com.spring.test.springtest.aop"})
public class Chapter4Application {

    @Autowired
    public UserService userService;
    @Bean
    public MyAspect initAspect(){
        return new MyAspect();
    }

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(Chapter4Application.class,args);
        Chapter4Application object = new Chapter4Application();
        object.userService = context.getBean(Chapter4Application.class).userService;
        System.out.println("-----开始");
        context.getBean(Chapter4Application.class).userService.printUser(new User());
        System.out.println("---开始新一轮测试，异常");
        context.getBean(Chapter4Application.class).userService.printUser(null);

        System.out.println("---"+context.getBean(Chapter4Application.class).userService);
        System.out.println("---"+context.getBean(MyAspect.class));
    }
}
