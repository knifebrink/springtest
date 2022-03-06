package com.spring.test.chapter6.rocketMq;

import com.spring.test.chapter6.rabbitMq.RabbitMqApplication;
import com.spring.test.chapter6.utils.SpringBootApplicationNoDataSources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplicationNoDataSources
public class RocketMqApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(RocketMqApplication.class, args);
    }
    @Autowired
    private ProducerService producerService;

    @PostConstruct
    public void fun(){
//        firstTest();
        sendWhile();
    }

    public void firstTest(){
        boolean result = producerService.send("demo-topic", "demo-TAG", "Hello RocketMQ");
        producerService.send("demo-topic", "demo-TAG", "这是第二条信息");

        log.info("发送：{}",result);
    }

    /**
     * 不断发送，测试可靠性
     */
    public void sendWhile()   {
        for(int i = 0; i < 10; i++){

//            boolean result = producerService.send("demo-topic", "demo-TAG", "i的值: "+i);
//            log.info("发送：{}",result);
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
