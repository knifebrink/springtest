package com.spring.test.chapter6.injection;

import com.spring.test.chapter6.chapter6.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnBean(First.class) //需要先把 First 依赖注入
public class NumberConfig {

//    @Bean
//    public Second getSecond(){
//        return new Second();
//    }

}
