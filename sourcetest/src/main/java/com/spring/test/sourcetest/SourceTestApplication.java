package com.spring.test.sourcetest;

import com.spring.test.sourcetest.controller.TestController;
import com.spring.test.sourcetest.controller.User;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;

@SpringBootApplication
public class SourceTestApplication {

	/**
	 * 可以从返回的对象中ApplicationContext获取beanFactory，然后获取装载进去的对象实例
	 * 该当前测试中工厂类为{@link DefaultListableBeanFactory}
	 * 1. getBean(.class)
	 * 2. getBeanNamesForType() 找到名字
	 * 3.
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SourceTestApplication.class, args);
		System.out.println("超级工厂对象："+context);
		AnnotationConfigServletWebServerApplicationContext aContext = (AnnotationConfigServletWebServerApplicationContext) context;
		System.out.println("获取的实例对象："+aContext.getBean(TestController.class));
		try {
			System.out.println("获取的实例对象：" + aContext.getBean(User.class));
		}catch (Exception e){}
		ConfigurableListableBeanFactory beanFactory=aContext.getBeanFactory();
		DefaultListableBeanFactory aBeanFactory = (DefaultListableBeanFactory) beanFactory;

		// 显示所有装载的实例名字
		for (Iterator<String> it = aBeanFactory.getBeanNamesIterator(); it.hasNext(); ) {
			String name = it.next();
			System.out.println(name);
		}

		aBeanFactory.getBean("uss");
		aBeanFactory.getBean(User.class);
		aBeanFactory.getBeanNamesForType(User.class);
		System.out.println(""+	aBeanFactory.getBean("uss"));




//		context.stop();

	}

}
