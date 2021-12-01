package com.spring.test.springtest.springtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/12/1 9:03
 */
@Configuration
public class SpringConfig {
    @Bean("sss")
    public SpringUser initUser(){
        SpringUser user = new SpringUser();
        user.setCount(1);
        user.setUserName("SpringConfig中用bean注入的属性");
        return user;
    }


}
