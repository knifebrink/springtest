package com.spring.test.chapter6.mybatis;

import com.spring.test.chapter6.chapter6.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import javax.annotation.PostConstruct;

/**
 * @author andgo
 * @date 2022/7/4
 */
//@SpringBootApplication(exclude = {
//        MongoAutoConfiguration.class,
//        RedisAutoConfiguration.class,
//        RedisRepositoriesAutoConfiguration.class,
//        RabbitAutoConfiguration.class,
//
//})
public class MybatisSyncApplication {

    public static void main(String[] args){
        SpringApplication.run(MybatisSyncApplication.class,args);
    }

    @PostConstruct
    public void postConstruct(){
        try {
            testSync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Autowired
    UserMapper2 userMapper2;

    public void testSync() throws InterruptedException {
        for(int i=0;i<100;i++){
            final int j = i;
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    batchInsertSync(j);
                }
            };
            thread.start();
        }
        Thread.sleep(30000);
    }

    public void batchInsertSync(int j){
        for (int i=0;i<1000;i++){
            userMapper2.insertUser3(new User(""+j,i));
        }
    }
}
