package com.spring.test.springtest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/7/5 15:45
 */
@Configuration
@ComponentScan(lazyInit = true) //开启懒加载模式
public class AppConfig4 {
}
