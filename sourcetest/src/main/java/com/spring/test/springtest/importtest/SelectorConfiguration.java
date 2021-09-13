package com.spring.test.springtest.importtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/9/13 18:26
 */
@Configuration
public class SelectorConfiguration {
    @Bean
    public Lan getLan(){
        return new Lan();
    }
}
