package com.spring.test.springtest;

//import com.spring.test.sourcetest.controller.TestController;
import com.spring.test.springtest.test.AllBeans;
import com.spring.test.springtest.test.SimpleMovieLister;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SourceTestApplication {
	@Autowired
	private ApplicationContext applicationContext;
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SourceTestApplication.class, args);
		System.out.println("start-------\n");
		SimpleMovieLister simpleMovieLister = context.getBean(SimpleMovieLister.class);
//		SimpleMovieLister simpleMovieLister = new SimpleMovieLister();
//		context.getBeanFactory().registerSingleton("simpleMovieLister",simpleMovieLister);
		System.out.println("自动装载的context："+context.getBean(SourceTestApplication.class).applicationContext);
		System.out.println(""+simpleMovieLister.user);
		System.out.println("@Primary测试:"+context.getBean(AllBeans.class).userP);
		System.out.println("@Value测试:"+context.getBean(AllBeans.class).valueTest);
		System.out.println(""+context.getBean(AppConfig.class));



		System.out.println("\nend---------\n");
		context.stop();

	}


}
