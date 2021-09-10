package com.spring.test.springtest.importtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/9/10 17:30
 *
 */
@Configuration
public class ColorRegistrarConfiguration {

    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

}
