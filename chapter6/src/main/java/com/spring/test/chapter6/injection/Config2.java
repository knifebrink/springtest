package com.spring.test.chapter6.injection;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "fch") // 这个不是条件，这是获取属性的。
public class Config2 {
}
