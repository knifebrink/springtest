package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.chapter6.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 重复插入一千次 23秒
 * sql语句批量插入 93ms ....
 */
//@SpringBootApplication(exclude = {
//        MongoAutoConfiguration.class,
//        RedisAutoConfiguration.class,
//        RedisRepositoriesAutoConfiguration.class,
//        RabbitAutoConfiguration.class
//
//})
@Slf4j
public class MybatisProApplication {
    public static void main(String[] args){
        SpringApplication.run(MybatisProApplication.class,args);


    }

    @Autowired
    UserProMapper userProMapper;
//    @PostConstruct
    public void fun(){
        test1();
        test2();
    }

    public void test1(){
        long time = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            User user = new User("name-"+i,i);
            userProMapper.insertUser(user);
        }
        log.warn("单个重复插入时间是：{}",System.currentTimeMillis() - time);
    }

    public void test2(){
        List<User> list = new ArrayList<>();
        long time = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            User user = new User("name-"+i,i);
            list.add(user);
        }
        userProMapper.insertUserBatch(list);
        log.warn("批量插入时间是：{}",System.currentTimeMillis() - time);
    }
}
