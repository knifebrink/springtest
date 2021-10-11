package com.spring.test.springtest.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 有哪些注入方式。那注解和.xml配置是哪种方式。
 */
@SpringBootApplication
public class Test2ApplicationTest {
    public static void main(String[] args){
        ConfigurableApplicationContext context =  SpringApplication.run(Test2ApplicationTest.class,args);
        testScope(context);
//        context.stop();
    }

    private static void testScope(ConfigurableApplicationContext context){
        Person person = context.getBean(Person.class);
//        System.out.println("是不是同一条狗："+(person.dog==person.dog2));
    }
}
