package com.spring.test.chapter6.chapter10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;

/**
 * 类型转换测试，
 * 大部分参数都经过转换器，采用注册机制
 */
@SpringBootApplication(scanBasePackages = {"com.spring.test.chapter6.chapter10"})
public class Chapter10Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter10Application.class, args);
	}



}
