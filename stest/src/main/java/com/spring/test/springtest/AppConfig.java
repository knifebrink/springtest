package com.spring.test.springtest;

import com.spring.test.springtest.test.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/7/5 15:45
 */
@Configuration
public class AppConfig {
    @Bean("sss")
    public User initUser(){
        User user = new User();
        user.setCount(1);
        user.setUserName("用bean注入的属性");
        return user;
    }
}
