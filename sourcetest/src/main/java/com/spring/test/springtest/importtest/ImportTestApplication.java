package com.spring.test.springtest.importtest;

//import com.spring.test.sourcetest.controller.TestController;

import com.spring.test.springtest.controller.User;
import org.springframework.asm.ClassReader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;

/**
 * 装配三种方式
 * 	使用模式注解 @Component 等（Spring2.5+）
 * 	使用配置类 @Configuration 与 @Bean （Spring3.0+）
 * 	使用模块装配 @EnableXXX 与 @Import （Spring3.1+）
 *
 * @link @Import 测试 ,@EnableXXXX，据说是模块装配
 *
 * 手动装配测试
 */
public class ImportTestApplication {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ColorConfig.class);
		String[] colors = annotationConfigApplicationContext.getBeanNamesForType(Color.class);
		System.out.println("---------------------------");
		for (String color : colors) {
			System.out.println("加载的颜色有："+color);
		}
	}


}
