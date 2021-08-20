package com.spring.test.sourcetest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/7/5 15:45
 */
@Configuration
public class AppConfig {
    @Bean
    public User initUser(){
        User user = new User();
        user.setId(1L);
        user.setName("用bean注入的属性");
        return user;
    }
}
