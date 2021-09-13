package com.spring.test.springtest.autotest.model;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author brink
 * 2021/9/13 17:21
 */
@Configuration
//@ComponentScan(basePackages = {"com.spring.test.springtest.autotest.model.sub"})
@ComponentScan // 默认值是当前根目录
public class ComScanTest {
    public static void main(String[] args){
//        AnnotationConfigServletWebServerApplicationContext context =
//                new AnnotationConfigServletWebServerApplicationContext(ComScanTest.class);
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ComScanTest.class);

    }

}
