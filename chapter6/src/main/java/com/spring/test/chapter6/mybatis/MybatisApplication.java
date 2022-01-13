package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.redis.RedisApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brink
 * 2022/1/13 15:56
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class MybatisApplication {
    public static void main(String[] args){
        SpringApplication.run(MybatisApplication.class,args);


    }

    @Autowired
    UserMapper2 userMapper2;
    Logger logger = LoggerFactory.getLogger("fch");
    @PostConstruct
    public void fun(){
        System.out.println("success");
//        logger.warn(""+userMapper2.selectUserResultMap());
//        logger.warn("这是测试if标签：{}",userMapper2.selectUserIf(1));
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        logger.warn("这是测试foreach标签：{}",userMapper2.selectUserForEach(list));
        logger.warn("这是测试sql标签：{}",userMapper2.selectUserSqlInclude());
    }
}
