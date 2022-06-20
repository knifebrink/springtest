package com.spring.test.chapter6.springbase;

import com.spring.test.chapter6.rabbitMq.RabbitMqApplication;
import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

/**
 * 异步任务注解@Async，配合@EnableAsync使用
 * 默认情况下，Spring 使用SimpleAsyncTaskExecutor去执行这些异步方法（此执行器没有限制线程数）。此默认值可以从两个层级进行覆盖
 * https://blog.csdn.net/xiaoxiaole0313/article/details/104666789
 */
@SpringBootApplicationNoDataSources
@Slf4j
public class SpringBaseApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBaseApplication.class,args);

    }
    @Autowired
    AsyncTest asyncTest;
    @Autowired
    AsyncTest2 asyncTest2;

    @PostConstruct
    public void AsyncTest(){
        log.info("object: {}",asyncTest);
        log.info("object: {}",(asyncTest.getClass()));

        log.info("object: {}",asyncTest2);
        asyncTest.doSomeThingAsync();
        asyncTest.doSomeThing();
        asyncTest.doInMyself();
    }
}
