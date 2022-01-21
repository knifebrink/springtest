package com.spring.test.chapter6.chapter6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * 事务测试
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class Chapter6Application {
	@Autowired
	PlatformTransactionManager transactionManager;
	public static void main(String[] args) {
		SpringApplication.run(Chapter6Application.class, args);
	}

	@PostConstruct
	public void viewTM(){
		System.out.println("----:"+transactionManager.getClass().getName());
	}

}
