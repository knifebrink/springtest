package com.spring.test.springtest.mybatistest;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.executor.SimpleExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author brink
 * 2021/9/16 9:49
 */
@SpringBootApplication
public class MyBatisTestApplication {

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MyBatisTestApplication.class,args);
        TalkUserMapper talkUserMapper = applicationContext.getBean(TalkUserMapper.class);
        System.out.println(talkUserMapper);
        talkUserMapper.selectAll();
        talkUserMapper.selectByName("brink");

        talkUserMapper.toString();


        TalkUserMapper talkUserMapper2 = applicationContext.getBean(TalkUserMapper.class);

        System.out.println(talkUserMapper2);

        TalkUserBean talkUserBean = applicationContext.getBean(TalkUserBean.class);
        talkUserBean.toString();
        applicationContext.stop();
    }

    private static void someClass(){
        MapperProxy mapperProxy = null;
        SimpleExecutor simpleExecutor;
    }
}
